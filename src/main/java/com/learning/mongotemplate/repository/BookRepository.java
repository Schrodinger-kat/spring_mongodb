package com.learning.mongotemplate.repository;

import com.learning.mongotemplate.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Books, Integer> {
  List<Books> findByNameContaining(String title);
}
