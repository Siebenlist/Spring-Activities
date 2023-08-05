package com.example.demo.controllers;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;

@Controller
public class BooksController {
	private final BookService bookService;

	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books")
	public String home(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "home";
	}

	@GetMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book){
		return "newbook";
	}

	@PostMapping("/books")
	public String create(@Valid @ModelAttribute("book") Book book, BindingResult result){
		if(result.hasErrors()){
			return "newbook";
		} else {
			bookService.createBook(book);
			return "redirect:/books";
		}
	}

	@GetMapping("/books/{id}")
	public String showBook(@PathVariable("id") Long id, Book book, Model model){
		Book b = bookService.findBook(id);
		model.addAttribute("book", b);
		System.out.println(b);
		return "bookdetails";
	}

	@GetMapping("/books/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "edit";
	}
}
