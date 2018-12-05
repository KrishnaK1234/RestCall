package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.model.Response;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@RestController
public class Controller {
	private String empId;
	private String empName;
	private String empDep;
	private String empSal;
	private Response response;

	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> insertData(@RequestBody Employee employee) throws Exception {
		empId = employee.getEmpId();
		empName = employee.getEmpName();
		empDep = employee.getEmpDep();
		empSal = employee.getEmpSal();

		MongoClient mongo = new MongoClient("localhost", 27017);
		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("Employees");

		BasicDBObject insert = new BasicDBObject();
		insert.append("EmpId", empId);
		insert.append("EmpName", empName);
		insert.append("EmpDep", empDep);
		insert.append("EmpSal", empSal);
		collection.insert(insert);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/insertMap", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> insert(@RequestBody Employee employee) throws Exception {
		empId = employee.getEmpId();
		empName = employee.getEmpName();
		empDep = employee.getEmpDep();
		empSal = employee.getEmpSal();

		MongoClient mongo = new MongoClient("localhost", 27017);
		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("Employees");

//		BasicDBObject insert = new BasicDBObject();
//		insert.append("EmpId", empId);
//		insert.append("EmpName", empName);
//		insert.append("EmpDep", empDep);
//		insert.append("EmpSal", empSal);
//		collection.insert(insert);

		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("database", "test");
		documentMap.put("collection", "Employees");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EmpId", empId);
		map.put("EmpName", empName);
		map.put("EmpDep", empDep);
		map.put("EmpSal", empSal);

		documentMap.put("details", map);
		collection.insert(new BasicDBObject(documentMap));
		
		response.setEmpId(empId);
		response.setEmpName(empName);
		response.setEmpDep(empDep);
		response.setEmpSal(empSal);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
