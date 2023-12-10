package com.tm.auctionapi.service;

import com.tm.auctionapi.model.Auction;
import com.tm.auctionapi.model.Participant;
import com.tm.auctionapi.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private AuctionService auctionService;

    public Participant saveParticipant(Participant p){
        return participantRepository.save(p);
    }

    public List<Participant> findAllParticipants(){
        return participantRepository.findAll();
    }

    public List<Participant> getAll(){
        var participantsList = participantRepository.findAll();
        return participantsList;
    }

    public Participant findById(Long id) {
        return participantRepository.findById(id).get();
    }

    public Participant insertParticipantInAuction(Long participantId, Long idAuction){
        Auction auct = new Auction();
        auct = auctionService.findById(idAuction);
        Participant p = participantRepository.getReferenceById(participantId);
        auct.getParticipants().add(p);
        auctionService.saveAuction(auct);

        return p;

    }

}
