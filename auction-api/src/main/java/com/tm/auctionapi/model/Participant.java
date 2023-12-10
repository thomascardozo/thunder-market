package com.tm.auctionapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "auctions")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isAuthenticated;

    @ManyToMany(mappedBy = "participants")
    @JsonBackReference
    private List<Auction> auctions = new ArrayList<>();

    // construtores, getters e setters

}
