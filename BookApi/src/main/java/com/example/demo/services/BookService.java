package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> findAll(){
    	return bookRepository.findAll();
    }
    
    public Book createBook(Book b) {
    	return bookRepository.save(b);
    }
    
    public Book findBook(Long id){
    	Optional<Book> optionalBook = bookRepository.findById(id);
    	if(optionalBook.isPresent()) {
    		return optionalBook.get();
    	} else {
    		return null;
    	}
    }

	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		Optional<Book> optionalBook = bookRepository.findById(id);
	
			Book b = optionalBook.get();
			b.setTitle(title);
			b.setDescription(desc);
			b.setLanguage(lang);
			b.setNumberOfPages(numOfPages);
			return bookRepository.save(b);

	}

	public void deleteBook(Long id) {
		if(bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
		}
	}
}
