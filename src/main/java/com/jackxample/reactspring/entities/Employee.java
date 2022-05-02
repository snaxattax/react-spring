package com.jackxample.reactspring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="employee")
public class Employee{
	
	
	//@GeneratedValue
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //testing?
	private Long id;
	
	@NotBlank(message = "First Name Required")
	private String firstName;
	
	@NotBlank(message = "First Name Required")
	private String lastName;
	
	private String description;
	
}