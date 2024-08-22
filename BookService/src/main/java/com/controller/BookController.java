package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Book;
import com.service.BookService;

@RestController
@RequestMapping("/bookapi")
public class BookController {
	
	@Autowired
	BookService Bookservice;
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book c)
	{
		Bookservice.addBook(c);
		ResponseEntity<Book> re=new ResponseEntity<Book>(c,HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getBooks")
	public ResponseEntity<List<Book>> getBooks()
	{
		List<Book> lc1=Bookservice.getBooks();
		ResponseEntity<List<Book>> re=new ResponseEntity<List<Book>>(lc1,HttpStatus.OK);
		return re;
	}
	
	@GetMapping(path="/getBook/{bid}")
	public ResponseEntity<Book> getBookById(@PathVariable int bid) throws Throwable
	{
		Book c1=Bookservice.getBookById(bid);
		
		ResponseEntity<Book> re=new ResponseEntity<Book>(c1,HttpStatus.OK);
		return re;
	}
	
	@PostMapping(path="/addBooks")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> ls)
	{
		Bookservice.addBooks(ls);
		
		ResponseEntity<List<Book>> re=new ResponseEntity<List<Book>>(ls,HttpStatus.OK);
		return re;
	}
	
	@PutMapping(path="/updateBook")
	public ResponseEntity<Book> updateBook(@RequestBody Book e) throws Throwable
	{
		Book e1=Bookservice.updateBook(e);
		
		ResponseEntity<Book> re=new ResponseEntity<Book>(e1,HttpStatus.OK);
		return re;
	}
	
	@DeleteMapping(path="/deleteBook")
	public ResponseEntity<String> deleteBook(@RequestBody Book e)
	{
		Bookservice.deleteBook(e);
		
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	@DeleteMapping(path="/deleteBookById/{eid}")
	public ResponseEntity<String> deleteBookById(@PathVariable int eid)
	{
		Bookservice.deleteBookById(eid);
		
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
	
	  @GetMapping("/getBookname/{bname}")
	  public ResponseEntity<Book>  getBookByCname(@PathVariable String bname) 
	  { 
		  Book c=Bookservice.getBookByBname(bname); 
		  ResponseEntity<Book> re=new ResponseEntity<Book>(c,HttpStatus.OK); 
		  return re;   
	  }
	  
	  @GetMapping("/getBookstech/{tech}") 
	  public ResponseEntity<List<Book>> findByTechSorted(@PathVariable String tech) 
	  { 
		  List<Book> lc=Bookservice.findByTechSorted(tech); 
		  ResponseEntity<List<Book>> re=new ResponseEntity<List<Book>>(lc,HttpStatus.OK); 
		  return re; 
		  }	
}
