package com.mercedes.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

	@Autowired
	ApplicationConfigReader appConfigReader;

	@Override
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString(appConfigReader.getMongoURI());
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	@Override
	protected String getDatabaseName() {
		return appConfigReader.getMongoDBName();
	}
}
