/*
 * Copyright dick the deployer.
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
package com.dickthedeployer.dick.web.service;

import com.dickthedeployer.dick.web.dao.BuildDao;
import com.dickthedeployer.dick.web.dao.JobBuildDao;
import com.dickthedeployer.dick.web.domain.Build;
import com.dickthedeployer.dick.web.domain.JobBuild;
import com.dickthedeployer.dick.web.model.dickfile.Dickfile;
import com.dickthedeployer.dick.web.model.dickfile.Job;
import com.dickthedeployer.dick.web.model.dickfile.Stage;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author mariusz
 */
@Slf4j
@Service
public class JobBuildService {

    @Autowired
    CommandService commandService;

    @Autowired
    WorkerService workerService;

    @Autowired
    JobBuildDao jobBuildDao;

    @Autowired
    BuildDao buildDao;

    @Async
    public void buildStage(Build build, Dickfile dickfile, Stage stage) {
        List<Job> jobs = dickfile.getJobs(stage);
        jobs.stream()
                .forEach(job -> workerService.sheduleJobBuild(build, job));
    }

    public Page<JobBuild> getJobBuilds(int page, int size) {
        return jobBuildDao.findAll(new PageRequest(page, size));
    }

}
