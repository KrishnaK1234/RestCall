package com.javainuse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.beans.Request;
import com.javainuse.beans.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class MongoController {

	@Autowired
	private MongoConfig mongo;

	private Response response;
	private DBCollection collection;
	private String id;
	private String name;
	private String age;

	// @RequestMapping(value = "/insert", method = RequestMethod.POST, produces
	// = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insert Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> insertData(@RequestBody Request req) throws Exception {

		id = req.getId();
		name = req.getName();
		age = req.getAge();

		collection = mongo.mongoTemplate().getCollection("Student");
		DBObject query = new BasicDBObject("Std_ID", id);
		DBCursor c1 = collection.find(query);

		try {
			int count = 0;
			while (c1.hasNext()) {
				System.out.println(c1.next());
				count++;
				break;
			}

			if (count > 0) {
				response = new Response();
				response.setId(id);
				response.setName(name);
				response.setAge(age);
				response.setDescription("Id is already exists in the database");

			} else {
				BasicDBObject insert = new BasicDBObject();
				insert.append("Std_ID", id);
				insert.append("Std_Name", name);
				insert.append("Std_Age", age);

				collection.insert(insert);
				response = new Response();
				response.setId(id);
				response.setName(name);
				response.setAge(age);

				response.setDescription("Successfully inserted values in the database");
			}
		} finally {
			c1.close();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @RequestMapping(value = "/update", method = RequestMethod.POST, produces
	// = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateData(@RequestBody Request req) throws Exception {
		id = req.getId();
		name = req.getName();
		age = req.getAge();
		collection = mongo.mongoTemplate().getCollection("Student");
		DBObject query = new BasicDBObject("Std_ID", id);
		DBCursor c1 = collection.find(query);
		try {
			int count = 0;
			while (c1.hasNext()) {
				System.out.println(c1.next());
				count++;
				break;
			}

			if (count == 0) {
				response = new Response();
				response.setId(id);
				response.setName(name);
				response.setAge(age);
				response.setDescription("ID is not exists in the database");

			} else {
				BasicDBObject update = new BasicDBObject();
				update.append("Std_ID", id);
				update.append("Std_Name", name);
				update.append("Std_Age", age);
				collection.update(query, update);

				response = new Response();
				response.setId(id);
				response.setName(name);
				response.setAge(age);
				response.setDescription("Successfully Updated values in the Database");
			}
		} finally {
			c1.close();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @RequestMapping(value = "/retrive/{id}", method = RequestMethod.GET,
	// consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrive Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@PostMapping(value = "/retrive", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> retriveData(@RequestBody Request req) throws Exception {
		id = req.getId();
		collection = mongo.mongoTemplate().getCollection("Student");

		DBObject query = new BasicDBObject("Std_ID", id);
		DBCursor c1 = collection.find(query);
		try {
			int count = 0;
			while (c1.hasNext()) {
				System.out.println(c1.next());
				count++;
				break;
			}

			if (count == 0) {
				response = new Response();
				response.setId(id);
				response.setDescription("ID is not exists in the database");

			} else {
				for (DBObject obj1 : c1) {
					id = (String) obj1.get("Std_ID");
					name = (String) obj1.get("Std_Name");
					age = (String) obj1.get("Std_Age");
				}
				response = new Response();
				response.setId(id);
				response.setName(name);
				response.setAge(age);
				response.setDescription("Successfully Retrive values from the Database");
			}
		} finally {
			c1.close();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET,
	// consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteData(@RequestBody Request req) throws Exception {
		id = req.getId();
		collection = mongo.mongoTemplate().getCollection("Student");
		DBObject query = new BasicDBObject("Std_ID", id);
		DBCursor c1 = collection.find(query);
		try {
			int count = 0;
			while (c1.hasNext()) {
				System.out.println(c1.next());
				count++;
				break;
			}
			if (count == 0) {
				response = new Response();
				response.setId(id);
				response.setDescription("ID is not exists in the database");

			} else {
				WriteResult result = collection.remove(query);
				System.out.println("Number of documents are deleted : " + result.getN());

				response = new Response();
				response.setId(id);
				response.setName(name);
				response.setAge(age);
				response.setDescription("Successfully delete the values from the Database");
			}
		} finally {
			c1.close();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
