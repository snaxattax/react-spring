package com.jackxample.reactspring.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jackxample.reactspring.entities.Employee;
import com.jackxample.reactspring.repositories.EmployeeRepository;

@Component
public class DatabaseLoader implements CommandLineRunner{
	private final EmployeeRepository repository;
	
	@Autowired
	public DatabaseLoader(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void run(String... strings) throws Exception{
		if(repository.count()==0) {
			Employee firstEmp = new Employee();
			firstEmp.setFirstName("Mister");
			firstEmp.setLastName("Coolguy");
			firstEmp.setDescription("Super Cool Dude, really");
			this.repository.save(firstEmp);
			System.out.println(firstEmp);
		}
	}
}