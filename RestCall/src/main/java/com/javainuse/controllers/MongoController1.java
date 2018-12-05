package com.javainuse.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.javainuse.beans.Employee;
import com.javainuse.beans.Request;
import com.javainuse.beans.Response;
import com.javainuse.beans.ResponseEmp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class MongoController1 {

	@Autowired
	private MongoConfig mongo;

	private ResponseEmp response;
	private DBCollection collection;
	private String empId;
	private String empName;
	private String empDep;
	private String empSal;
	private String Description;

	@ApiOperation(value = "Insert Operation", response = ResponseEmp.class, tags = {
			"Spring_Boot_Rest_API_Mongodb" })
	@ApiResponse(code = 200, message = "OK", response = ResponseEmp.class)
	@PostMapping(value = "/insertEmployees", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEmp> insertData(@RequestBody Employee req) throws Exception {

		empId = req.getEmpId();
		empName = req.getEmpName();
		empDep = req.getEmpDep();
		empSal = req.getEmpSal();

		collection = mongo.mongoTemplate().getCollection("Employees");
		DBObject query = new BasicDBObject("EmpId", empId);
		DBCursor c1 = collection.find(query);

		try {
			int count = 0;
			while (c1.hasNext()) {
				System.out.println(c1.next());
				count++;
				break;
			}

			if (count > 0) {
				response = new ResponseEmp();
				response.setEmpId(empId);
				response.setEmpName(empName);
				response.setEmpDep(empDep);
				response.setEmpSal(empSal);
				response.setDescription("Id is already exists in the database");

			} else {
				
				// BASIC DBObect example
//				BasicDBObject insert = new BasicDBObject();
//				insert.append("EmpId", empId);
//				insert.append("EmpName", empName);
//				insert.append("EmpDep", empDep);
//				insert.append("EmpSal", empSal);
//				collection.insert(insert);

				// map example
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

				response = new ResponseEmp();
				response.setEmpId(empId);
				response.setEmpName(empName);
				response.setEmpDep(empDep);
				response.setEmpSal(empSal);
				response.setDescription("Successfully inserted values in the database");
			}
		} finally {
			c1.close();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
