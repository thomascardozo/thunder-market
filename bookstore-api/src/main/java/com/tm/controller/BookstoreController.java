package com.tm.controller;

import com.tm.model.Book;
import com.tm.proxy.CambioProxy;
import com.tm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {


    private Environment env;


    private BookService bookService;


    private final CambioProxy proxy;

    public BookstoreController(Environment env, BookService bookService, CambioProxy proxy) {
        this.env = env;
        this.bookService = bookService;
        this.proxy = proxy;
    }

    @GetMapping("{id}/{currency}")//
    public Book getOk(@RequestHeader String accessToken,
                      @PathVariable("id") Long id,
                      @PathVariable("currency") String currency){

        var book = bookService.findById(id, accessToken).get();

        if (book == null) throw new RuntimeException("Book not Found");


        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = env.getProperty("local.server.port");
        book.setEnvironment(
                "Book port: " + port +
                        " Cambio Port " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());

        return book;
    }


}
