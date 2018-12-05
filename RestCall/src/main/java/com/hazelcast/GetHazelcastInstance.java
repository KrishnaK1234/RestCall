package com.hazelcast;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.javainuse.beans.Employee;
import com.javainuse.controllers.MongoConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.google.gson.Gson;

//@ComponentScan({ "com.*" })
public class GetHazelcastInstance {
//	@Autowired
//	private static MongoConfig mongo;
//	private static DBCollection collection;

	public static void main(String[] args) throws Exception {
		DBCollection collection;
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
		IMap<Object, Object> map = hzInstance.getMap("testing");
		Employee employee = new Employee();
		employee.setEmpId("123");
		employee.setEmpName("Devil");
		employee.setEmpDep("DeepForest");
		employee.setEmpSal("1000000");

		map.put("key", employee);
//		System.out.println("successfully insert");
//		System.out.println(map.get("key"));

		Gson gson = new Gson();
		String json = gson.toJson(map.get("key"));
		// Parse to bson document and insert
		Document doc = Document.parse(json);

		MongoConfig mongo = new MongoConfig();
		collection = mongo.mongoTemplate().getCollection("Employees");
		collection.insert(new BasicDBObject("id", doc));

	}
}
