package com.example.demo.entity;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



//@Data
@Entity
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long empId;
	private String name;
	private String empDepatment;

	public Employee(int i, String ram, String it) {

	}

	public Employee(Long empId, String name, String empDepatment) {
		this.empId = empId;
		this.name = name;
		this.empDepatment = empDepatment;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpDepatment() {
		return empDepatment;
	}

	public void setEmpDepatment(String empDepatment) {
		this.empDepatment = empDepatment;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", empDepatment=" + empDepatment + "]";
	}

}
