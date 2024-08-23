package com.config;

import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.service.StudentService;

@Configuration
public class RouterConfig {
	  @Autowired
	    private StudentService service;
	    
	    @Bean
	    RouterFunction<ServerResponse> router(){
	        return RouterFunctions.route(RequestPredicates.POST("/save"), service::saveStudent)
	                .andRoute(RequestPredicates.POST("/search"), service::findStudent)
	                .andRoute(RequestPredicates.POST("/all"), service::findAll)
	                .andRoute(RequestPredicates.POST("/delete"), service::deleteStudent);
	    }

}
