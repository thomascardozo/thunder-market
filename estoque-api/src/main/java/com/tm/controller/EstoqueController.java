package com.tm.controller;

import com.tm.exception.EstoqueNotFoundException;
import com.tm.model.Estoque;
import com.tm.service.EstoqueService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {


    private Environment env;


    private EstoqueService estoqueService;

    public EstoqueController(Environment env, EstoqueService estoqueService) {
        this.env = env;
        this.estoqueService = estoqueService;
    }

    @GetMapping("{id}")
    public Estoque getEstoqueById(@RequestHeader String accessToken,
                      @PathVariable("id") Long id){

        var estoqueItem = estoqueService.findById(id, accessToken);

        if (estoqueItem == null || estoqueItem.isEmpty()) throw new EstoqueNotFoundException("Estoque not Found");

        return estoqueItem.get();
    }

    @GetMapping("/item/{itemId}")
    public Estoque getEstoqueById(@RequestHeader String accessToken,
                                  @PathVariable("itemId") UUID itemId){

        var estoqueItem = estoqueService.findByItemId(itemId, accessToken);

        if (estoqueItem == null || estoqueItem.isEmpty()) throw new EstoqueNotFoundException("Estoque not Found");

        return estoqueItem.get();
    }



    public List<ResponseEntity> getStockBooks(@RequestHeader String accessToken,
                                              @PathVariable("ids") List<Long> id){

        return null;
    }


}
