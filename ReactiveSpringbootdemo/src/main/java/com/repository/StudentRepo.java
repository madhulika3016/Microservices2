package com.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;

import com.entity.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@EnableReactiveMongoRepositories
public interface StudentRepo extends ReactiveMongoRepository<Student, String>
{
	@Query("select * from student where name=?1")
    Mono<Student> findByName(String name);

}
