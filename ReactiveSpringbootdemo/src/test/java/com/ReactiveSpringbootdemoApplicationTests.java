package com;

import static org.mockito.ArgumentMatchers.any; 
import static org.mockito.Mockito.when; 
  
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.boot.test.mock.mockito.MockBean; 
import org.springframework.http.MediaType; 
import org.springframework.test.web.reactive.server.WebTestClient; 
import org.springframework.web.reactive.function.server.ServerResponse; 

import com.entity.Student;
import com.service.StudentService;

@SpringBootTest
@AutoConfigureWebTestClient
class WebFluxApplicationTests { 
    @Autowired
    private WebTestClient webTestClient; 
      
    @MockBean
    private StudentService serviceHandler; 
      
      
    @Test
    public void testaddStudent() { 
        Student student = new Student(); 
        student.setName("John"); 
        student.setAge("21"); 
          
        when(serviceHandler.saveStudent(any())).thenReturn(ServerResponse.ok().bodyValue(student)); 
        webTestClient.post().uri("/save").contentType(MediaType.APPLICATION_JSON) 
        .bodyValue(student).exchange().expectStatus().isOk().expectBody(Student.class) 
        .isEqualTo(student); 
    } 
      
    @Test
    public void testdeleteStudentById() { 
        Student student = new Student(); 
        student.setId("1234"); 
  
        when(serviceHandler.deleteStudent(any())).thenReturn(ServerResponse.ok().bodyValue(student)); 
        webTestClient.post().uri("/delete").contentType(MediaType.APPLICATION_JSON) 
        .bodyValue(student).exchange().expectStatus().isOk().expectBody(Student.class) 
        .isEqualTo(student); 
    } 
  
    @Test
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
    public void testgetAllStudents() { 
        Student student = new Student(); 
  
        when(serviceHandler.findAll(any())).thenReturn(ServerResponse.ok().bodyValue(student)); 
        webTestClient.post().uri("/all").contentType(MediaType.APPLICATION_JSON) 
        .bodyValue(student).exchange().expectStatus().isOk().expectBody(Student.class) 
        .isEqualTo(student); 
    } 
} 
