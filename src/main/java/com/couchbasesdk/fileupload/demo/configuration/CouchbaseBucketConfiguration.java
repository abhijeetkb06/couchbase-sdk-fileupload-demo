package com.couchbasesdk.fileupload.demo.configuration;

import com.couchbasesdk.fileupload.demo.model.LoadFile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

@Configuration
@Profile("!unit-test")
public class CouchbaseBucketConfiguration extends AbstractCouchbaseConfiguration {

    private final CouchbaseClusterProperties couchbaseClusterProperties;

    public CouchbaseBucketConfiguration(CouchbaseClusterProperties couchbaseClusterProperties) {
        this.couchbaseClusterProperties = couchbaseClusterProperties;
    }

    @Override
    public String getConnectionString() {
        return couchbaseClusterProperties.getConnectionString();
    }

    @Override
    public String getUserName() {
        return couchbaseClusterProperties.getUserName();
    }

    @Override
    public String getPassword() {
        return couchbaseClusterProperties.getPassword();
    }

    @Override
    public String getBucketName() {
        return couchbaseClusterProperties.getBucketPDFStore().getName();
    }

    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping
                    .mapEntity(LoadFile.class, loadFileTemplate());
        } catch (Exception e) {
            throw e;
        }
    }

    @Bean("loadFileClientFactory")
    public CouchbaseClientFactory loadFileClientFactory() {
        return new SimpleCouchbaseClientFactory(getConnectionString(), authenticator(), couchbaseClusterProperties.getBucketPDFStore().getName());
    }

    @Bean("loadFileTemplate")
    public CouchbaseTemplate loadFileTemplate(){
        return new CouchbaseTemplate(loadFileClientFactory(), new MappingCouchbaseConverter());
    }
}
