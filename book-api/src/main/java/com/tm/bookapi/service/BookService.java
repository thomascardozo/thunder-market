package com.tm.bookapi.service;

import com.tm.bookapi.model.Book;
import com.tm.bookapi.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private final JWTService jwtService;

    public BookService(BookRepository repository, JWTService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public void delete(Book book) {
        log.info("Excluding book as ID: {}", book.getId());
        repository.delete(book);
    }


    public Optional<Book> findById(Long id) {// , String accessToken

//        jwtService.validateAccessToken(accessToken);
//        var authUser = jwtService.getAuthUserResponse(accessToken);
        var ok = HttpStatus.OK;

        return repository.findById(id);
    }

    public Book getById(Long id) {
        return repository.getById(id);
    }


}
