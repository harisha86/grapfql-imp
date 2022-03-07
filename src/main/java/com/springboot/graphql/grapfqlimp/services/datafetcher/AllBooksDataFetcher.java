package com.springboot.graphql.grapfqlimp.services.datafetcher;

import java.util.List;

import com.springboot.graphql.grapfqlimp.model.Book;
import com.springboot.graphql.grapfqlimp.repository.BooksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{

    @Autowired
    BooksRepository booksrepository;

    @Override
    public List<Book> get(DataFetchingEnvironment environment) {
        // Find all
        return booksrepository.findAll();
    }

    
    
}
