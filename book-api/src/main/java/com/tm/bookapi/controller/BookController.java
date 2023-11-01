package com.tm.bookapi.controller;

import com.tm.bookapi.model.Book;
import com.tm.bookapi.proxy.CambioProxy;
import com.tm.bookapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("book")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    BookService bookService;

//    @Autowired
//    private CambioProxy proxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
//            @RequestHeader String accessToken,
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency) {

        var book = bookService.findById(id).get();//,accessToken

        if (book == null) throw new RuntimeException("Book not Found");


//        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

//        var port = environment.getProperty("local.server.port");
//        book.setEnvironment(
//                "Book port: " + port +
//                        " Cambio Port " + cambio.getEnvironment());
//        book.setPrice(cambio.getConvertedValue());
        return book;
    }

    @GetMapping({"/",""})
    public String getOkk(){
        return "OK!";
    }

}
