package com.leandro.fernandez.apitest.controller;

import com.leandro.fernandez.apitest.exceptions.EmployeeNotFoundException;
import com.leandro.fernandez.apitest.repository.EmployeeRepository;
import com.leandro.fernandez.apitest.service.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;


    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        })
                .orElseGet(()-> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                        });



        /*OTRA FORMA DE HACERLO "MAS PRECARIO"
        Employee employee = repository.findById(id).orElse(null);

        if(employee ==null){
            throw new EmployeeNotFoundException(id);
        }
        employee.setId(id);
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);

         */


    }




}

