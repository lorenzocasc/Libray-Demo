package com.example.demoservice.service;

import com.example.demoservice.entity.Book;
import com.example.demoservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final StockServiceImpl stockService;

    //Save operation
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    //Read operation
    @Override
    public List<Book> fetchBookList() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public boolean checkBooks(Integer id){
        if(stockService.countBooks(id)>0){
            return true;  //C'è disponibilità in magazzino
        }else{
            return false; //Non c'è disponibilità in magazzino
        }
    }

    //Update operation
    @Override
    public Book
    updateBook(Book book,
               Integer id) {
        Book bookDB
                = bookRepository.findById(id)
                .get();

        if (Objects.nonNull(book.getBookName())
                && !"".equalsIgnoreCase(
                book.getBookName())) {
            bookDB.setBookName(
                    book.getBookName());
        }

        if (Objects.nonNull(
                book.getAuthor())
                && !"".equalsIgnoreCase(
                book.getAuthor())) {
            bookDB.setAuthor(
                    book.getAuthor());
        }

        if (Objects.nonNull(book.getPagesNumber())
                && !"".equalsIgnoreCase(
                book.getPagesNumber().toString())) {
            bookDB.setPagesNumber(
                    book.getPagesNumber());
        }

        book.setDate(new Date(System.currentTimeMillis()));

        return bookRepository.save(bookDB);
    }

    //Delete operation
    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

}
