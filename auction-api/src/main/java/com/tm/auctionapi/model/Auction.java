package com.tm.auctionapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"bids", "participants"})
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;
    private BigDecimal startingPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bid> bids = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "auction_participant",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    @JsonBackReference
    private List<Participant> participants = new ArrayList<>();

    public BigDecimal getCurrentHighestBid() {
        return bids.stream()
                .map(Bid::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }
}
