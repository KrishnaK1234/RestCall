//package com.controller;
//
//import static org.junit.Assert.*;
//
//import java.net.URLClassLoader;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//
//import com.model.Employee;
//import com.model.Response;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.MongoClient;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ Controller.class, MongoClient.class, DB.class, DBCollection.class })
//@PowerMockIgnore("javax.management.*")
//public class ControllerTest {
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		MongoClient mongo = PowerMockito.mock(MongoClient.class);
//		PowerMockito.whenNew(MongoClient.class).withAnyArguments().thenReturn(mongo);
//		DB db = PowerMockito.mock(DB.class);
//		PowerMockito.when(mongo.getDB("test")).thenReturn(db);
//		DBCollection dbCollection = PowerMockito.mock(DBCollection.class);
//		PowerMockito.when(db.getCollection("Employees")).thenReturn(dbCollection);
//	}
//
//	@Test
//	public void test() throws Exception {
//		Employee employee = new Employee();
//		employee.setEmpId("123");
//		employee.setEmpName("krishna");
//		employee.setEmpDep("Development");
//		employee.setEmpSal("17000");
//
//		Controller controller = new Controller();
//		controller.insertData(employee);
//	}
//
//}
