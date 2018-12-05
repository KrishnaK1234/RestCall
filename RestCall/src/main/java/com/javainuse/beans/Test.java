package com.javainuse.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Test {

	public static void main(String[] args) {

		Object id;
		Object name;
		Object age;

		// MongoClient client = new MongoClient("localhost", 27017);
		// MongoDatabase database = client.getDatabase("test");
		// MongoCollection<Document> collection = database
		// .getCollection("Student");
		//
		// List<Document> documents = (List<Document>) collection.find().into(
		// new ArrayList<Document>());
		//
		// for(Document document : documents){
		// System.out.println(document);
		// }

		// with default server and port adress
		MongoClient client = new MongoClient("localhost", 27017);
		DB db = client.getDB("test");
		DBCollection collection = db.getCollection("Student");
		DBCursor dbo = collection.find();

		String i = "1";
		int count = 0;
		for (DBObject dbobj : dbo) {
			id = dbobj.get("Std_ID");
			name = dbobj.get("Std_Name");
			age = dbobj.get("Std_Age");
			// System.out.println( id+ " " + name + " " + age);
			DBObject query = new BasicDBObject("Std_ID", i);
			System.out.println("=============================================");
			DBCursor c1 = collection.find(query);
			for (DBObject obj1 : c1) {
				id = obj1.get("Std_ID");
				name = obj1.get("Std_Name");
				age = obj1.get("Std_Age");
				List<Object> list = new ArrayList<Object>();
				list.add(id);
				list.add(name);
				list.add(age);
				System.out.println(list);
			}
System.out.println("=============================================");
			System.out.println(c1);
			try {
				while (c1.hasNext()) {
					System.out.println(c1.getCollection());
					// System.out.println(c1.next() + "\n id is already exist
					// ");
					count++;
					break;
				}
			} finally {
				c1.close();
			}
			System.out.println(c1);

		}

	}
}