package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmpoyeeServiceInterface {

	// add emp
	public Employee addEmployee(Employee emp);

	// get All Emp
	public List<Employee> getAllEmp();

	// get emp by Id
	public Employee getEmpbyId(Long empId);

	// update emp
	public Employee updateEmpbyId(Employee emp, Long id);

	// delete emp
	public String deleteEmpbyId(Long empId);

}
