package com.krishna.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.krishna.beans.EmployeeRequest;
import com.krishna.beans.EmployeeResponse;
import com.krishna.configuration.MongoConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class Controller {
	@Autowired
	private MongoConfig mongo;
	@Autowired
	private EmployeeResponse response;

	private DBCollection collection;

	@ApiOperation(value = "Insert Operation", response = EmployeeResponse.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = EmployeeResponse.class)
	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeResponse> putData(@RequestBody EmployeeRequest req) throws Exception {

		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
		IMap<Object, Object> map = hzInstance.getMap("testing");
		map.put("EmployeeData", req);
		Gson gson = new Gson();
		String json = gson.toJson(map.get("EmployeeData"));
		Document doc = Document.parse(json);
		collection = mongo.mongoTemplate().getCollection("Employees");
		collection.insert(new BasicDBObject("Employee", doc));
		setResponse(req);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * @param req
	 */
	private void setResponse(EmployeeRequest req) {
		response.setEmpId(req.getEmpId());
		response.setEmpName(req.getEmpName());
		response.setEmpDep(req.getEmpDep());
		response.setEmpSal(req.getEmpSal());
		response.setDescription("Successfully put the data into cache and mongodb");
	}

}
