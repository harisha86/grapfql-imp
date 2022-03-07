package com.springboot.graphql.grapfqlimp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Book {
    
    @Id
    public Long id;
    public String isn;
    public String title;
    public String publisher;
    public String[] authors;
    public String publishedDate;

}
