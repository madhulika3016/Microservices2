package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>
{
	Book findByBname(String bname);	
	 
 	@Query("Select b from Book b where b.tech=?1  order by b.bname")
	List<Book> findByTechSorted(String tech);

}
