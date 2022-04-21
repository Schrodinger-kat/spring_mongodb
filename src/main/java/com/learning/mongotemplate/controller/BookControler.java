package com.learning.mongotemplate.controller;

import com.learning.mongotemplate.model.Books;
import com.learning.mongotemplate.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class BookControler {

  @Autowired
  private BookRepository bookRepo;

  @GetMapping("/books")
  public ResponseEntity<List<Books>> getAllBooks(@RequestParam(required = false) String name) {

    List<Books> catalogue = new ArrayList<>();

    if (name == null) catalogue.addAll(bookRepo.findAll());
    else catalogue.addAll(bookRepo.findByNameContaining(name));

    if (catalogue.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(catalogue, HttpStatus.OK);
  }

  @GetMapping("/books/{id}")
  public ResponseEntity<Books> getBookById(@PathVariable("id") Integer id) {

    Optional<Books> data = bookRepo.findById(id);

    if (data.isPresent()) {
      return new ResponseEntity<>(data.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/books/{id}")
  public ResponseEntity<Books> addBooks(@RequestBody Books book) {
    try {
      Books books =
          bookRepo.save(
              new Books(
                  book.getId(), book.getName(), book.getPages(), book.getAuthor(), book.getCost()));
      return new ResponseEntity<>(books, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
