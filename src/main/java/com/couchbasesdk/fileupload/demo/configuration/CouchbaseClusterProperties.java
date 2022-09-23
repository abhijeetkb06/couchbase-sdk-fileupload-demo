package com.couchbasesdk.fileupload.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "couchbase-config")
public class CouchbaseClusterProperties {

    private String connectionString;
    private String userName;
    private String password;

    @NestedConfigurationProperty
    private CouchbaseBucketProperties bucketPDFStore;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CouchbaseBucketProperties getBucketPDFStore() {
        return bucketPDFStore;
    }

    public void setBucketPDFStore(CouchbaseBucketProperties bucketPDFStore) {
        this.bucketPDFStore = bucketPDFStore;
    }

}
