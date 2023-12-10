package com.tm.auctionapi.service;

import com.tm.auctionapi.model.Auction;
import com.tm.auctionapi.model.Bid;
import com.tm.auctionapi.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionService auctionService;

    public Bid saveBid(Bid bid){
        if(bid.getAuction() != null && bid.getAuction().getEndTime().isAfter(LocalDateTime.now())){
            return bidRepository.save(bid);
        }
        return null;
    }

    public List<Bid> findAllBids(){
        return bidRepository.findAll();
    }

    public Bid findById(Long id) {
        return bidRepository.findById(id).get();
    }

    @Transactional
    public void placeBid(Bid bid, Long auctionId, Long bidderId) {
//        Auction auction = entityManager.find(Auction.class, auctionId);

        Auction auction = auctionService.findById(auctionId);

        if (auction != null && auctionService.isAuctionOpen(auction.getId())
                && auctionService.isBidAmountValid(auction.getId(), bid.getAmount())) {
            Bid bidToSave = new Bid();
            bidToSave.setAmount(bid.getAmount());
            bidToSave.setBidderId(bidderId);
            bidToSave.setAuction(auction);
            auction.getBids().add(bidToSave);

            bidRepository.save(bidToSave);

//            entityManager.persist(bid);
        }
    }
}
