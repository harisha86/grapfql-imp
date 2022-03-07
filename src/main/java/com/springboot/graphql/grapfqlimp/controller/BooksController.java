package com.springboot.graphql.grapfqlimp.controller;

import com.springboot.graphql.grapfqlimp.services.GraphQLService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;

@RequestMapping("/rest/books")
@RestController
public class BooksController {

    @Autowired
    GraphQLService graphqlService;

    
    @PostMapping("/getallbooks")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult executeResult = graphqlService.getGraphQL().execute(query);
        return new ResponseEntity<>(executeResult, HttpStatus.OK);
    }

}
