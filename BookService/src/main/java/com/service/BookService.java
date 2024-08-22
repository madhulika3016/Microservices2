package com.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.ResourceNotFoundException;
import com.entity.Book;
import com.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	BookRepo repo;
	
	public Book addBook(Book c)
	{
		 repo.save(c);	
		 return c;
	}
	
	public List<Book> getBooks()
	{
		List<Book> lc1=repo.findAll();
		
		return lc1;
	}

	public Book getBookById(int cid) throws Throwable {
		Supplier s1= ()->new ResourceNotFoundException("Book Does not exist in the database");
		Book c=repo.findById(cid).orElseThrow(s1);
		return c;
	}

	public String deleteBookById(int cid) {
		
		repo.deleteById(cid);
		
		return "Deleted";
	}

	public String deleteBook(Book c) {
		
		repo.delete(c);
		return "Deleted";
	}

	public Book updateBook(Book c) throws Throwable {
		int id=c.getBid();
		
		Supplier s1= ()->new ResourceNotFoundException("Book Does not exist in the database");
		Book c1=repo.findById(id).orElseThrow(s1);
		
		c1.setBname(c.getBname());
			c1.setTech(c.getTech());
			repo.save(c1);
			return c1;	
	}

	public List<Book> addBooks(List<Book> ls) {
		repo.saveAll(ls);
		return ls;
	}
	
	public Book getBookByBname(String bname) {
		Book c=repo.findByBname(bname);
		return c;
	}
	
	public List<Book> findByTechSorted(String tech) 
	{
		List<Book> lc=repo.findByTechSorted(tech);
	return lc;
	}

}
