package com.springboot.graphql.grapfqlimp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Book {

    
    @Id
    public String isn;
    public String title;
    public String publisher;
    public String[] authors;
    public String publishedDate;   

    public Book(String isn, String title, String publisher, String[] authors, String publishedDate) {
        this.isn = isn;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }

}

