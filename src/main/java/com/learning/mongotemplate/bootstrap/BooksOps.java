package com.learning.mongotemplate.bootstrap;

import com.learning.mongotemplate.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksOps implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.save(new Books(500, "Core Java", 200, "Kathy Sierra", 1065.5));
        mongoTemplate.save(new Books(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0));
        mongoTemplate.save(new Books(502, "Spring in Action", 480, "Craig Walls", 940.75));
        mongoTemplate.save(new Books(503, "Pro Angular", 260, "Freeman", 1949.25));
        mongoTemplate.save(new Books(504, "HTML CSS", 100, "Thomas Powell", 2317.09));
        mongoTemplate.save(new Books(505, "Hibernate in Action", 180, "Gavin King", 889.25));
        mongoTemplate.save(new Books(506, "Practical MongoDB", 180, "Shakuntala Gupta", 785.0));
        mongoTemplate.save(new Books(507, "Pro Spring Boot", 850, "Felipe Gutierrez", 2167.99));
        mongoTemplate.save(new Books(508, "Beginning jQuery", 180, "Franklin", 1500.00));
        mongoTemplate.save(new Books(509, "Java Design Patterns", 114, "Devendra Singh", 919.99));

        System.out.println("------All records has been saved successfully-------");

        mongoTemplate.insert(List.of(
                        new Books(500, "Core Java", 200, "Kathy Sierra", 1065.5),
                        new Books(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0),
                        new Books(502, "Spring in Action", 480, "Craig Walls", 940.75),
                        new Books(503, "Pro Angular", 260, "Freeman", 1949.25),
                        new Books(504, "HTML CSS", 100, "Thomas Powell", 2317.09),
                        new Books(505, "Hibernate in Action", 180, "Gavin King", 889.25),
                        new Books(506, "Practical MongoDB", 180, "Shakuntala Gupta", 785.0),
                        new Books(507, "Pro Spring Boot", 850, "Felipe Gutierrez", 2167.99),
                        new Books(508, "Beginning jQuery", 180, "Franklin", 1500.00),
                        new Books(509, "Java Design Patterns", 114, "Devendra Singh", 919.99)
                ),
                "bookstore"
        );

        List<Books> list = mongoTemplate.findAll(Books.class);
        //List<Book> list = mt.findAll(Book.class,"Book");  //If collection name & the Entity Class Name are different (case-sensitive)
        list.forEach(System.out::println);

        Books book = mongoTemplate.findById(504, Books.class);
//Book book = mt.findById(501, Book.class,"Book");
        System.out.println(book);

        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(501));

        Update update = new Update();
        update.set("cost", 1065.25);
        update.set("name", "Core Java");

        mongoTemplate.findAndModify(query, update, Books.class);
        System.out.println("Data Modified");

        Query query2= new Query();
        query2.addCriteria(Criteria.where("pages").lte(180));
        Update update2 = new Update();
        update2.set("cost", 999.0);
        mongoTemplate.updateMulti(query2, update2, Books.class);

        Query query3= new Query();
        query3.addCriteria(Criteria.where("cost").is(1749.0));
        mongoTemplate.findAndRemove(query3, Books.class);

        Query query4= new Query();
        query4.addCriteria(Criteria.where("cost").gte(1000.0));
        mongoTemplate.findAllAndRemove(query4, Books.class);

        Query query5= new Query();
        query5.addCriteria(Criteria.where("id").is(510));
        Update update5 = new Update();
        update5.set("cost", 1065.25);
        update5.set("name", "Core Java");
        mongoTemplate.upsert(query5, update5, Books.class);
    }
}
