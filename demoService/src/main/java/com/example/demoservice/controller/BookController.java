package com.example.demoservice.controller;


import com.example.demoservice.entity.Book;
import com.example.demoservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public void set(@Valid @RequestBody Book book) {
        // assign parameters to taskDocumentEntity by constructor args or setters
        bookService.saveBook(book);
    }

    //Save operation
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Book saveBook(
            @Valid @RequestBody Book book) {
        return bookService.saveBook(book);
    }


    //Read operation
    @RequestMapping(value = "fetch", method = RequestMethod.GET)
    public List<Book> fetchBookList() {
        return bookService.fetchBookList();
    }

    //Update operation
    @RequestMapping(value = "update/{id:.+}", method = RequestMethod.POST)
    public Book updateBook(@RequestBody Book book, @PathVariable("id") Integer id) {
        return bookService.updateBook(book, id);
    }

    //Delete operation
    @RequestMapping(value = "delete/{id:.+}", method = RequestMethod.POST)
    public String deleteBookById(@PathVariable("id") Integer id) {
        bookService.deleteBookById(id);
        return "Deleted Successfully";
    }


}
