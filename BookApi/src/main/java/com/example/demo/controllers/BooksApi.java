package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;

@RestController
public class BooksApi {
    private final BookService bookService;
    public BooksApi(BookService bookService){
        this.bookService = bookService;
    }

    //Index de API
    @RequestMapping("/api/books")
    public List<Book> index() {
        return bookService.findAll();
    }


    //Solicitud para crear libros
    @RequestMapping(value="/api/books", method= RequestMethod.POST)
    public Book create(@RequestParam(value="title") String title,
                       @RequestParam(value="description") String desc,
                       @RequestParam(value="language") String lang,
                       @RequestParam(value="pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.createBook(book);
    }

    //Solicitud para encontrar libros
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }

    //Solicitud para editar libros
    @PutMapping(value = "/api/books/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Long id,
                                       @RequestParam(value = "title") String title,
                                       @RequestParam(value = "description") String desc,
                                       @RequestParam(value = "language") String lang,
                                       @RequestParam(value = "pages") Integer numOfPages) {
        Book updatedBook = bookService.updateBook(id, title, desc, lang, numOfPages);
        return ResponseEntity.ok(updatedBook);
    }

    //Solicitud para eliminar libros
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}