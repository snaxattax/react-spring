package com.jackxample.reactspring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.jackxample.reactspring.entities.Employee;

//@Repository
@RepositoryRestResource(collectionResourceRel = "employee")
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
}