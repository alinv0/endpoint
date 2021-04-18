package com.example.endpoint;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.endpoint.account",
                                        "com.example.endpoint.transaction"})
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "endpoint-test";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example.endpoint");
    }
}
