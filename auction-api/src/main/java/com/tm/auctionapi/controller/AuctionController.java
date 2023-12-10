package com.tm.auctionapi.controller;

import com.tm.auctionapi.model.Auction;
import com.tm.auctionapi.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {

        @Autowired
        private AuctionService auctionService;

        @PostMapping
        public ResponseEntity<Void> createAuction(@RequestBody Auction auction) {
            auctionService.createAuction(auction);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    @PostMapping("/save")
    public ResponseEntity<Void> saveAuction(@RequestBody Auction auction) {
        auctionService.saveAuction(auction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

        @GetMapping
        public ResponseEntity<List<Auction>> getAllAuctions() {
//            List<Auction> auctions = auctionService.getAllAuctions();//
            List<Auction> auctions = auctionService.findAllAuctions();
            return new ResponseEntity<>(auctions, HttpStatus.OK);
        }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> findOneAuction(@RequestParam("id") Long id) {
        Auction auction = auctionService.findById(id);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }



        // Adicione outros métodos conforme necessário para notificações, processamento de vencedores, etc.

}
