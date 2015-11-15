/*
 * Copyright 2015 Pivotal Software, Inc..
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
package com.dickthedeployer.dick.web.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author mariusz
 */
@Data
@Entity
public class StackEntity {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String projectName;
    
    private String repository;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    
    private String server;
    
    private String ref;

    public static class Builder {

        private final StackEntity item;
        
        public Builder() {
            this.item = new StackEntity();
        }

        public Builder withId(final Long id) {
            this.item.id = id;
            return this;
        }

        public Builder withProjectName(final String projectName) {
            this.item.projectName = projectName;
            return this;
        }

        public Builder withRepository(final String repository) {
            this.item.repository = repository;
            return this;
        }

        public Builder withCreationDate(final Date creationDate) {
            this.item.creationDate = creationDate;
            return this;
        }

        public Builder withServer(final String server) {
            this.item.server = server;
            return this;
        }

        public Builder withRef(final String ref) {
            this.item.ref = ref;
            return this;
        }

        public StackEntity build() {
            return this.item;
        }
    }

    
}
