/*
 * Copyright 2015 dick the deployer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dickthedeployer.dick.web;

import org.kohsuke.randname.RandomNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mariusz
 */
@EnableAsync
@Controller
@EnableScheduling
@SpringBootApplication
@EnableFeignClients
@PropertySource(value = "META-INF/maven/com.dickthedeployer/dick-web/pom.properties", ignoreResourceNotFound = true)
@PropertySource(value = "git.properties", ignoreResourceNotFound = true)
public class Application {

    @Bean
    public RandomNameGenerator randomNameGenerator() {
        return new RandomNameGenerator();
    }

    @RequestMapping(value = {"/{path:[^\\.]*}",
            "/{path:[^\\.]*}/{path:[^\\.]*}",
            "/{path:[^\\.]*}/{path:[^\\.]*}/{path:[^\\.]*}",
            "/{path:[^\\.]*}/{path:[^\\.]*}/{path:[^\\.]*}/{path:[^\\.]*}"})
    public String redirect() {
        return "forward:/";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
