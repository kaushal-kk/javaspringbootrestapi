package com.example.demo.Controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repo.EmployeeRepository;
import com.example.demo.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
    private EmployeeRepository employeeRepository;

	@PostMapping("/saveemployee")
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        employeeRepository.save(employee);
        return employee;
    }
	
	@PostMapping("/saveallemployee")
	 public String saveAllEmployee(@RequestBody List<Employee> employees)
    {
        employeeRepository.saveAll(employees);
        return "Saved";
    }

    @GetMapping("/getallemployee")
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();

    }

  //@GetMapping(path="/getemployeebyid/{id}",produces="application/xml")
    @GetMapping("/getemployeebyid/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id)
    {
        return employeeRepository.findById(id);
    }
   
    @PutMapping("/updateemployee")
    public Employee UpdateEmployee(@RequestBody Employee employee)
    {
        employeeRepository.save(employee);
        return employee;
    }
    
    @DeleteMapping("/deleteemployee/{id}")
    public String DeleteEmployee(@PathVariable int id) 
    {
    	Employee emp=employeeRepository.getById(id);
    	employeeRepository.delete(emp);
    	
    	return "Deleted";
    }
}
