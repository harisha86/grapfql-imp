package com.springboot.graphql.grapfqlimp.services.datafetcher;

import com.springboot.graphql.grapfqlimp.model.Book;
import com.springboot.graphql.grapfqlimp.repository.BooksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book>{

    @Autowired
    BooksRepository booksrepository;

    @Override
    public Book get(DataFetchingEnvironment environment) {
        // Find one
        String isn = environment.getArgument("idfromgraphql");
        return booksrepository.findById(isn).get();
    }
    
}
