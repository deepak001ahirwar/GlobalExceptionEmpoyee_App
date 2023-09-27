package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmptyInputException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.EmpCURDRepo;

@Service
public class EmpoyeeServiceImp implements EmpoyeeServiceInterface {

	@Autowired
	private EmpCURDRepo empRepo;

	@Override
	public Employee addEmployee(Employee emp) {

		if (emp.getName().isEmpty() || emp.getName().length() == 0) {
			throw new EmptyInputException("Empoyee name does  not correct or not be empty ", "Bad request ",
					new Object[] { emp });
		}

		Employee empDto = empRepo.save(emp);
		return empDto;
	}

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> empList = empRepo.findAll();
		return empList;
	}

	@Override
	public Employee getEmpbyId(Long empId) {
		Employee emp = empRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Empoyee does not exits ", "Resource not found ",
						new Object[] { empId }));

//		if (emp.isEmpty()) {
//			throw new ResourceNotFoundException("Empoyee does not exits ", "Resource not found ",
//					new Object[] { empId });
//		}
		return emp;
	}

	@Override
	public Employee updateEmpbyId(Employee emp, Long id) {
		Employee originalEmp = empRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Empoyee does not exits ", "Resource not found ",
						new Object[] { emp }));
//		if (originalEmp.isEmpty()) {
//			throw new ResourceNotFoundException("Empoyee does not exits ", "Resource not found ",
//					new Object[] { emp });
//		}
		if(emp.getName()!=null) {
			originalEmp.setName(emp.getName());
		}
		if(emp.getEmpDepatment()!=null) {
			originalEmp.setEmpDepatment(emp.getEmpDepatment());	
		}
		empRepo.save(originalEmp);
		return originalEmp;
	}

	@Override
	public String deleteEmpbyId(Long empId) {
		
		Employee originalEmp = empRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Empoyee does not exits ", "Resource not found ",
						new Object[] { empId }));
		
		empRepo.deleteById(empId);
		return "empId " + empId + " has been sucessfully delete ";
	}

}
