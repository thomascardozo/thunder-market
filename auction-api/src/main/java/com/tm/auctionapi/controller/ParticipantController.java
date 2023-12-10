package com.tm.auctionapi.controller;

import com.tm.auctionapi.model.Auction;
import com.tm.auctionapi.model.Participant;
import com.tm.auctionapi.service.AuctionService;
import com.tm.auctionapi.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

        @Autowired
        private ParticipantService participantService;

        @Autowired
        private AuctionService auctionService;

        @PostMapping("/{idParticipant}/{idAuction}")
        public ResponseEntity<Participant> createParticipant(@PathVariable ("idParticipant") Long idParticipant,
                                                         @PathVariable ("idAuction") Long idAuction) {
//            bidService.createAuction(bid);
            ;
            return new ResponseEntity<>(participantService.insertParticipantInAuction(idParticipant,idAuction),
                    HttpStatus.CREATED);
        }


        @GetMapping("/all")
        public List<Participant> getAllParticipants() {
            List<Participant> participants = participantService.getAll();
            return participants;
        }

        @GetMapping("/{participantId}")
        public ResponseEntity<Participant> getOneParticipantById(
                @RequestParam ("participantId") Long participantId) {

            return new ResponseEntity<>(participantService.findById(participantId),HttpStatus.OK);
        }

        @PostMapping("/{idAuction}")
    public ResponseEntity<Participant> saveParticipant(@RequestBody Participant participant,
                                                       @PathVariable Long idAuction){
            var savedParticipant = participantService.saveParticipant(participant);
            Auction auction = auctionService.findById(idAuction);
            auction.getParticipants().add(savedParticipant);
            auctionService.saveAuction(auction);
            return ResponseEntity.ok(savedParticipant);
        }

        // Adicione outros métodos conforme necessário para notificações, processamento de vencedores, etc.

}
