package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.entity.Student;
import com.repository.StudentRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
	
	@Autowired
    private StudentRepo repo;

    public Mono<ServerResponse> saveStudent(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> {
            return repo.save(data).flatMap(done -> {
                return ServerResponse.ok().bodyValue("Student Saved Successfully \n" + data);
            });
        });
    }

    public Mono<ServerResponse> findStudent(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> {
            return repo.findById(data.getId()).flatMap(done -> {
                return ServerResponse.ok().bodyValue(done);
            });

        }).onErrorResume(e -> {
            return ServerResponse.badRequest().bodyValue("Student ID Not Found");
        });
    }
    
    public Mono<ServerResponse> updateStudentById(ServerRequest request) { 
        return request.bodyToMono(Student.class).flatMap(data -> { 
            return repo.findById(data.getId()).flatMap(change -> { 
                change.setId(data.getId()); 
                change.setName(data.getName()); 
                change.setAge(data.getAge()); 
                return ServerResponse.ok().body(repo.save(change), Student.class); 
            }).switchIfEmpty(ServerResponse.ok().bodyValue("No Student Data Found")); 
        }); 
    } 

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return repo.findAll().collectList().flatMap(done -> ServerResponse.ok().bodyValue(done))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("No Data Found"));
    }
    
    public Mono<ServerResponse> findbyname(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> {
            return repo.findByName(data.getName()).flatMap(done -> {
                return ServerResponse.ok().bodyValue(done);
            });

        }).onErrorResume(e -> {
            return ServerResponse.badRequest().bodyValue("Student Name Not Found");
        });
    }

    public Mono<ServerResponse> deleteStudent(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> {
            return repo.deleteById(data.getId()).then(ServerResponse.ok().bodyValue("Student Details Deleted"))
                    .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Student ID Not Found"));
        });
    }

}
