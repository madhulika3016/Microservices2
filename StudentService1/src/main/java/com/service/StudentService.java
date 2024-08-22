package com.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.ResourceNotFoundException;
import com.entity.Student;
import com.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repo;
	
	public Student addStudent(Student c)
	{
		 repo.save(c);	
		 return c;
	}
	
	public List<Student> getStudents()
	{
		List<Student> lc1=repo.findAll();
		
		return lc1;
	}

	public Student getStudentById(int cid) throws Throwable {
		Supplier s1= ()->new ResourceNotFoundException("Student Does not exist in the database");
		Student c=repo.findById(cid).orElseThrow(s1);
		return c;
	}

	public String deleteStudentById(int cid) {
		
		repo.deleteById(cid);
		
		return "Deleted";
	}

	public String deleteStudent(Student c) {
		
		repo.delete(c);
		return "Deleted";
	}

	public Student updateStudent(Student c) throws Throwable {
		int id=c.getSid();
		
		Supplier s1= ()->new ResourceNotFoundException("Student Does not exist in the database");
		Student c1=repo.findById(id).orElseThrow(s1);
		
		c1.setSname(c.getSname());
			c1.setTech(c.getTech());
			repo.save(c1);
			return c1;	
	}

	public List<Student> addStudents(List<Student> ls) {
		repo.saveAll(ls);
		return ls;
	}
	
	public Student getStudentBySname(String sname) {
		Student c=repo.findBySname(sname);
		return c;
	}
	
	public List<Student> findByTechSorted(String tech) 
	{
		List<Student> lc=repo.findByTechSorted(tech);
	return lc;
	}
	
	

}
