package com.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.entity.Student;

@Repository
public interface StudentRepo extends ReactiveMongoRepository<Student, String>
{

}
