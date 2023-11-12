package com.tm.service;

import com.tm.dto.SimpleResponse;

import com.tm.model.Estoque;
import com.tm.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private Environment environment;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    public EstoqueService(EstoqueRepository repository, Environment environment, JwtService jwtService) {
        this.repository = repository;
        this.environment = environment;
        this.jwtService = jwtService;
    }


    public List<Estoque> findAll() {
        return null;
    }


    public void delete(Estoque book) {

    }


    public Optional<Estoque> findById(Long id, String accessToken) {
        SimpleResponse sr = getData(accessToken);
        System.out.println("PRINTANDO O USER >>: " + sr.authUser());
        return repository.findById(id);
    }

    public Optional<Estoque> findByItemId(UUID itemId, String accessToken) {
        SimpleResponse sr = getData(accessToken);
        System.out.println("PRINTANDO O USER >>: " + sr.authUser());
        return repository.findByItemId(itemId);
    }

    public SimpleResponse getData(String accessToken){
        jwtService.validateAccessToken(accessToken);
        var authUser = jwtService.getAuthUserResponse(accessToken);
        var ok = HttpStatus.OK;
        return new SimpleResponse(ok.name(), ok.value(), authUser);
    }

    public Estoque getById(Long id) {
        return repository.getById(id);
    }


}
