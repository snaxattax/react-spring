package com.jackxample.reactspring.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackxample.reactspring.entities.Employee;
import com.jackxample.reactspring.repositories.EmployeeRepository;


@RestController
@RequestMapping("/employees")
public class EmployeesController {
	private final EmployeeRepository employeeRepository;
	
	public EmployeesController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	// get all emps
	@GetMapping
	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	// cute way to throw in one line
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return employeeRepository.findById(id).orElseThrow(RuntimeException::new);
	}
	
	@PostMapping
	public ResponseEntity createEmployee(@RequestBody Employee employee) throws URISyntaxException {
		Employee savedEmployee = employeeRepository.save(employee);
		return ResponseEntity.created(new URI("/employees/" + savedEmployee.getId())).body(savedEmployee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee currentEmployee = employeeRepository.findById(id).orElseThrow(RuntimeException::new);
		
		currentEmployee.setFirstName(employee.getFirstName());
		currentEmployee.setLastName(employee.getLastName());
		currentEmployee.setDescription(employee.getDescription());
		currentEmployee = employeeRepository.save(employee);
		
		return ResponseEntity.ok(currentEmployee);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}