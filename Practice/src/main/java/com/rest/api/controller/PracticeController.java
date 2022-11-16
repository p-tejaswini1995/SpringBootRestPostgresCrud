package com.rest.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.entity.Employee;

@RestController
@RequestMapping("/home")
public class PracticeController {
	
	@GetMapping("print")
	public String print() {
		return "Print";
	}
@PostMapping("/test")
public ResponseEntity<Employee> getDetails(@RequestBody Employee emp) {
	
	int id=emp.getId();
	if(id==1303874) {
		emp.setName("Tejaswini Pullammagari");
	}
	return ResponseEntity.ok(emp);
	
}
@PostMapping("/stream")
public ResponseEntity<List<Employee>> getHighSalary(@RequestBody Employee emp){
	List<Employee> empList= new ArrayList<>();
	List<Employee> result= new ArrayList<>();
	
	Employee emp1=new Employee(1,"Teju",1000);
	Employee emp2=new Employee(1,"Bhargav",2000);
	Employee emp3=new Employee(1,"Chinny",3000);
	Employee emp4=new Employee(1,"Mammulu",4000);
	
	empList.add(emp1);
	empList.add(emp2);
	empList.add(emp3);
	empList.add(emp4);
	result=empList.stream()
	.filter(e->e.getSalary()>2000)
	.collect(Collectors.toList());
	return  ResponseEntity.ok(result);
}
}
