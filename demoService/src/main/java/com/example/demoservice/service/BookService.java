package com.example.demoservice.service;

import com.example.demoservice.entity.Book;
import java.util.List;
public interface BookService {


    //Save operations
    Book saveBook(Book book);

    //Check the disponibility
    boolean checkBooks(Integer id);

    // Read operation
    List<Book> fetchBookList();
    //Update operation
    Book updateBook(Book book, Integer id);

    //Delete operation
    void deleteBookById(Integer id);
}
