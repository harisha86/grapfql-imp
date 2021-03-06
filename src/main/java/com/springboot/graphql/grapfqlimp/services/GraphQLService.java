package com.springboot.graphql.grapfqlimp.services;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import com.springboot.graphql.grapfqlimp.model.Book;
import com.springboot.graphql.grapfqlimp.repository.BooksRepository;
import com.springboot.graphql.grapfqlimp.services.datafetcher.AllBooksDataFetcher;
import com.springboot.graphql.grapfqlimp.services.datafetcher.BookDataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

    @Autowired
    BooksRepository bookRepository;

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Books into the Book Repository
        loadDataIntoHSQL();

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Book("123", "Book of Clouds", "Kindle Edition",
                        new String[] {
                        "Chloe Aridjis"
                        }, "Nov 2017"),
                new Book("124", "Cloud Arch & Engineering", "Orielly",
                        new String[] {
                                "Peter", "Sam"
                        }, "Jan 2015"),
                new Book("125", "Java 9 Programming", "Orielly",
                        new String[] {
                                "Venkat", "Ram"
                        }, "Dec 2016"),
                new Book("126", "Java 11 Programming", "Orielly",
                        new String[] {
                                "Alfred", "Ria","Rishi", "Damo"
                        }, "Dec 2016"),
                new Book("127", "Springboot", "Orielly",
                        new String[] {
                                "Rishi", "Damo"
                        }, "Dec 2017"),
                new Book("128", "Microservices", "Orielly",
                        new String[] {
                                "Singh", "Tishki"
                        }, "Dec 2018"),
                new Book("129", "Design Thinking", "Orielly",
                        new String[] {
                                "Venkat", "John", "Tom"
                        }, "Dec 2019"),
                new Book("130", "Machine Learnong", "Orielly",
                        new String[] {
                                "Harisha", "Selvaraj", "Chalam"
                        }, "Dec 2020")
).forEach(book -> {
            bookRepository.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
