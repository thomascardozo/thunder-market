package com.tm.auctionapi.controller;

import com.tm.auctionapi.model.Bid;
import com.tm.auctionapi.service.AuctionService;
import com.tm.auctionapi.service.BidService;
import com.tm.auctionapi.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {

        @Autowired
        private BidService bidService;

        @Autowired
        private AuctionService auctionService;

        @Autowired
        private ParticipantService participantService;


        @GetMapping
        public List<Bid> getAllBids() {
            List<Bid> bids = bidService.findAllBids();
            return bids;
        }

        @PostMapping("/add")
        public ResponseEntity<Void> placeBid(
                @RequestBody Bid bid,
                @RequestHeader (name = "auctionId") Long auctionId,
                @RequestHeader (name = "bidderId") Long bidderId) {
            bidService.placeBid(bid, auctionId, bidderId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
