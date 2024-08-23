package com.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "students")
public class Student {
	
	@MongoId
	String id;
	String name;
	String age;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
