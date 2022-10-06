package com.leandro.fernandez.apitest.repository;

import com.leandro.fernandez.apitest.service.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
