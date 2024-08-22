package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>
{

	@Query("Select s from Student s  where s.tech=?1  order by s.sname")
	List<Student> findByTechSorted(String tech);

	Student findBySname(String sname);

}
