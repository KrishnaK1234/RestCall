package com.javainuse.beans;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class Employee implements Serializable{
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empDep;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empSal;

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empDep
	 */
	public String getEmpDep() {
		return empDep;
	}

	/**
	 * @param empDep the empDep to set
	 */
	public void setEmpDep(String empDep) {
		this.empDep = empDep;
	}

	/**
	 * @return the empSal
	 */
	public String getEmpSal() {
		return empSal;
	}

	/**
	 * @param empSal the empSal to set
	 */
	public void setEmpSal(String empSal) {
		this.empSal = empSal;
	}

}
