package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmpCURDRepo extends JpaRepository<Employee, Long> {

}
