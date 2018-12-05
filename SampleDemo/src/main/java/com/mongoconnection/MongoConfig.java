//package com.mongoconnection;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.web.client.RestTemplate;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//
//@Configuration
//public class MongoConfig {
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//
//	@Bean
//	public MongoClient mongo() throws Exception {
//		ServerAddress serverAddress = new ServerAddress("localhost", 27017);
//		MongoCredential credential = MongoCredential.createCredential("krishna", "test", "krish".toCharArray());
//
//		@SuppressWarnings("deprecation")
//		MongoClient client = new MongoClient(serverAddress, Arrays.asList(credential));
//		return client;
//	}
//
//	@Bean
//	public MongoTemplate mongoTemplate() throws Exception {
//		return new MongoTemplate(mongo(), "test");
//
//	}
//
//}