package com.tm.auctionapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
//@EqualsAndHashCode(exclude = "auction")  // Excluir a referência circular
//@ToString(exclude = "auction")  // Excluir a referência circular
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "bidder_id")
    private Long bidderId;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    @JsonBackReference
    private Auction auction;

    // Construtores, getters e setters

}

