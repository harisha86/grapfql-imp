package com.springboot.graphql.grapfqlimp.repository;

import com.springboot.graphql.grapfqlimp.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long>{
    
}
