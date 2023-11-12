package com.tm.controller;

import com.tm.exception.BookNotFoundException;
import com.tm.exception.CurrencyUnsupportedException;
import com.tm.model.Book;
import com.tm.proxy.CambioProxy;
import com.tm.service.BookService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Book getBookWithValueConvertedFromUSDToLocalCurrency(@RequestHeader String accessToken,
                                                                @PathVariable("id") UUID id, // Long id
                                                                @PathVariable("currency") String currency){

        var book = bookService.findById(id, accessToken);

        if (book == null) throw new BookNotFoundException("Book not Found"); // book.isEmpty()

        try {
            var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
            var port = env.getProperty("local.server.port");
            book.setEnvironment(
                    "Book port: " + port +
                            " Cambio Port " + cambio.getEnvironment());
            book.setPrice(cambio.getConvertedValue());
        } catch (Exception e) {
            throw new CurrencyUnsupportedException(e.getMessage());
        }

        return book;
    }

    public List<ResponseEntity> getStockBooks(@RequestHeader String accessToken,
                                              @PathVariable("id") Long id){
        return null;
    }


}
