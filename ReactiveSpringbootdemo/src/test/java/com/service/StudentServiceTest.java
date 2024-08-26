package com.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.entity.Student;


@SpringBootTest
@AutoConfigureWebTestClient
class StudentServiceTest {
	
	@Autowired
    private WebTestClient webTestClient; 
      
    @MockBean
    private StudentService serviceHandler; 

	@Test
	void testSaveStudent() {
	     Student student = new Student(); 
	        student.setName("John"); 
	        student.setAge("21"); 
	        
	        when(serviceHandler.saveStudent(any())).thenReturn(ServerResponse.ok().bodyValue(student));
	        
	        webTestClient.post().uri("/save").contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(student)
	            .exchange()
	            .expectStatus().isOk()
	            .expectBody(Student.class)
	            .consumeWith(response -> {
	                Student actualStudent = response.getResponseBody();
	                assertEquals(student.getName(), actualStudent.getName());
	                assertEquals(student.getAge(), actualStudent.getAge());
	            });
	    }
	
	
	@Test
    void testGetAllStudents() { 
        Student student = new Student(); 
        student.setId("1234");
        student.setName("John");
        student.setAge("21");

        when(serviceHandler.findAll(any())).thenReturn(ServerResponse.ok().bodyValue(student)); 
        
        webTestClient.post().uri("/all").contentType(MediaType.APPLICATION_JSON)
            .bodyValue(student)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Student.class)
            .consumeWith(response -> {
                Student actualStudent = response.getResponseBody();
                assertEquals(student.getId(), actualStudent.getId());
                assertEquals(student.getName(), actualStudent.getName());
                assertEquals(student.getAge(), actualStudent.getAge());
            });
    }
	
	//@Test
	 @Disabled
    public void testupdateStudentById() { 
        Student student = new Student(); 
        student.setId("1234"); 
        student.setName("John"); 
        student.setAge("21"); 
  
        when(serviceHandler.updateStudentById(any())).thenReturn(ServerResponse.ok().bodyValue(student)); 
        webTestClient.post().uri("/update").contentType(MediaType.APPLICATION_JSON) 
        .bodyValue(student).exchange().expectStatus().isOk().expectBody(Student.class) 
        .isEqualTo(student); 
    }
	 
	 @Test
	    void testDeleteStudentById() { 
	        Student student = new Student(); 
	        student.setId("1234"); 
	  
	        when(serviceHandler.deleteStudent(any())).thenReturn(ServerResponse.ok().bodyValue(student)); 
	        
	        webTestClient.post().uri("/delete").contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(student)
	            .exchange()
	            .expectStatus().isOk()
	            .expectBody(Student.class)
	            .consumeWith(response -> {
	                Student actualStudent = response.getResponseBody();
	                assertEquals(student.getId(), actualStudent.getId());
	            });
	    }
	 
	 
}


