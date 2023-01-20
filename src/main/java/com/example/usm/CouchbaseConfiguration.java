package com.example.usm;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.example.usm"})
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {


    @Override
    public String getConnectionString() {
        return "127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "couchbase";
    }

    @Override
    public String getBucketName() {
        return "serviceManagement";
    }

}
