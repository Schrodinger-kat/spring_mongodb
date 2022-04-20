package com.learning.mongotemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Books {

    @Id
    private Integer id;

    private String name;
    private Integer pages;
    private String author;
    private Double cost;
}
