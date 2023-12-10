package com.tm.auctionapi.service;

import com.tm.auctionapi.model.Auction;
import com.tm.auctionapi.repository.AuctionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AuctionRepository auctionRepository;

    @Transactional
    public void createAuction(Auction auction) {
        auction.setStartTime(LocalDateTime.now());
        entityManager.persist(auction);
    }

    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }

    public List<Auction> getAllAuctions() {
        return entityManager.createQuery("SELECT a FROM Auction a", Auction.class).getResultList();
    }

    public List<Auction> findAllAuctions(){
        return auctionRepository.findAll();
    }

    public Auction findById(Long id){
        return auctionRepository.findById(id).get();
    }



    public boolean isAuctionOpen(Long auctionId) {
        Auction auctionReturned = auctionRepository.findById(auctionId).get();
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(auctionReturned.getStartTime()) && now.isBefore(auctionReturned.getEndTime());
    }

    public boolean isBidAmountValid(Long auctionId, BigDecimal bidAmount) {
        Auction auctionReturned = auctionRepository.findById(auctionId).get();
        return bidAmount.compareTo(auctionReturned.getCurrentHighestBid()) > 0;
    }

    // Adicione métodos para notificações, processamento de vencedores, pagamento, entrega, etc.
}

