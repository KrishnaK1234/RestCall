package com.krishna.beans;

import org.springframework.stereotype.Component;

@Component
public class EmployeeResponse {
//	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empId;
//	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empName;
//	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empDep;
//	@JsonInclude(JsonInclude.Include.NON_NULL)
	String empSal;
	private String Description;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

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
