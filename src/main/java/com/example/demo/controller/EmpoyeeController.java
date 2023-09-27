package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpoyeeServiceInterface;

@RestController
@RequestMapping("/employee")
public class EmpoyeeController {

	@Autowired
	private EmpoyeeServiceInterface empservice;

	// add emp
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmpoyee (@Valid  @RequestBody Employee emp) {

		Employee empDto = empservice.addEmployee(emp);
		return new ResponseEntity<Employee>(empDto, HttpStatus.CREATED);
		// ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	// get all emp
	@GetMapping("/allEmpList")
	public ResponseEntity<List<Employee>> getAllEmpList() {
		List<Employee> listEmp = empservice.getAllEmp();
		return ResponseEntity.status(HttpStatus.OK).body(listEmp);
	}

	// get byid emp
	@GetMapping("/getEmpoById")
	public ResponseEntity<Employee> getEmpById(@RequestParam Long id) {
		Employee empDto = empservice.getEmpbyId(id);
		return new ResponseEntity<Employee>(empDto, HttpStatus.OK);
	}

	// dete by id
	@DeleteMapping("/deleteEmpoById")
	public ResponseEntity<String> deleteEmpById(@PathVariable Long id) {
		String mesage = empservice.deleteEmpbyId(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mesage);
	}

	// update emp
	@PutMapping("/updateEmp")
	public ResponseEntity<Employee> updateEmpoyee(@RequestBody Employee emp, @RequestParam Long id) {
		Employee empDto = empservice.updateEmpbyId(emp, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(empDto);
	}

}
