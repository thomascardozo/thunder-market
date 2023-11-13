package com.tm.service;

import com.tm.dto.SimpleResponse;
import com.tm.model.Book;
import com.tm.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private Environment environment;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    public BookService(BookRepository repository, Environment environment, JwtService jwtService) {
        this.repository = repository;
        this.environment = environment;
        this.jwtService = jwtService;
    }


    public List<Book> findAll() {
        return null;
    }


    public void delete(Book book) {

    }


    public Book findById(UUID id, String accessToken) {//Long id  |  Optional<Book>
        SimpleResponse sr = getData(accessToken);
        System.out.println("PRINTANDO O USER >>: " + sr.authUser());
        return repository.findByBookId(id);
        // repository.findById(id);
    }

    public SimpleResponse getData(String accessToken) {
        jwtService.validateAccessToken(accessToken);
        var authUser = jwtService.getAuthUserResponse(accessToken);
        var ok = HttpStatus.OK;
        return new SimpleResponse(ok.name(), ok.value(), authUser);
    }

    public Book getById(Long id) {
        return repository.getById(id);
    }


}
