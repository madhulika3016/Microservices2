package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.entity.Book;
import com.entity.RequiredResponse;
import com.entity.Student;
import com.service.StudentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/studentapi")
public class StudentController {
	
	@Autowired
	StudentService Studentservice;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student c)
	{
		Studentservice.addStudent(c);
		ResponseEntity<Student> re=new ResponseEntity<Student>(c,HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getStudents")
	public ResponseEntity<List<Student>> getStudents()
	{
		List<Student> lc1=Studentservice.getStudents();
		ResponseEntity<List<Student>> re=new ResponseEntity<List<Student>>(lc1,HttpStatus.OK);
		return re;
	}
	
	@GetMapping(path="/getStudent/{cid}")
	public ResponseEntity<Student> getStudentById(@PathVariable int cid) throws Throwable
	{
		Student c1=Studentservice.getStudentById(cid);
		
		ResponseEntity<Student> re=new ResponseEntity<Student>(c1,HttpStatus.OK);
		return re;
	}
	
	@PostMapping(path="/addStudents")
	public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> ls)
	{
		List<Student> le=Studentservice.addStudents(ls);
		
		ResponseEntity<List<Student>> re=new ResponseEntity<List<Student>>(le,HttpStatus.OK);
		return re;
	}
	
	@PutMapping(path="/updateStudent")
	public ResponseEntity<Student> updateStudent(@RequestBody Student e) throws Throwable
	{
		Student e1=Studentservice.updateStudent(e);
		
		ResponseEntity<Student> re=new ResponseEntity<Student>(e1,HttpStatus.OK);
		return re;
	}
	
	@DeleteMapping(path="/deleteStudent")
	public ResponseEntity<String> deleteStudent(@RequestBody Student e)
	{
		Studentservice.deleteStudent(e);
		
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	@DeleteMapping(path="/deleteStudentById/{eid}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int eid)
	{
		Studentservice.deleteStudentById(eid);
		
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	  @GetMapping("/getStudentname/{cname}")
	  public ResponseEntity<Student>  getStudentBySname(@PathVariable String sname) 
	  { 
		  Student c=Studentservice.getStudentBySname(sname); 
		  ResponseEntity re=new ResponseEntity<Student>(c,HttpStatus.OK); 
		  return re;   
	  }
	  
	  @GetMapping("/getStudentstech/{tech}") 
	  public ResponseEntity<List<Student>> findByTechSorted(@PathVariable String tech) 
	  { 
		  List<Student> lc=Studentservice.findByTechSorted(tech); 
		  ResponseEntity re=new ResponseEntity<List<Student>>(lc,HttpStatus.OK); 
		  return re; 
		  }
	  
			
 @GetMapping(path = "/id/{id}") 
 @CircuitBreaker(fallbackMethod = "getDataFallBack", name = "BOOK-SERVICE")
 public ResponseEntity<RequiredResponse>    getAllDadaBasedonStudentId(@PathVariable Integer id) throws Throwable{
			  RequiredResponse requiredResponse = new RequiredResponse(); 
			 Student s1 = Studentservice.getStudentById(id);
			  requiredResponse.setStudent(s1);
			  
			  // then get Book for Student
			  
	 Book book = restTemplate.getForObject("http://localhost:9090/bookapi/getBook/"+id,Book.class); 
			  requiredResponse.setBook(book); 
			  return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK); 
			  
 }
			
 
 public ResponseEntity<RequiredResponse>    getDataFallBack(@PathVariable Integer id,RuntimeException e) throws Throwable{
	  RequiredResponse requiredResponse = new RequiredResponse(); 
	 Student s1 = Studentservice.getStudentById(id);
	 requiredResponse.setStudent(s1);
	 return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
 }
	
	 
}
