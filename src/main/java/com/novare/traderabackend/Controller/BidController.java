package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Bid;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Repository.BidRepo;
import com.novare.traderabackend.Repository.TraderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {
    private static final Logger logger = LoggerFactory.getLogger(BidController.class);
    @Autowired
    private BidRepo bidRepo;
    @Autowired
    private TraderRepo traderRepo;
    @Autowired
    private AuctionRepo auctionRepo;

    @GetMapping
    public List<Bid> list() { return bidRepo.findAll(); }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bid> create(@RequestBody final Bid bid){
       // logger.info(String.valueOf(bid.getOwner_id()));

        return new ResponseEntity<>(bidRepo.saveAndFlush(bid), HttpStatus.CREATED);
    }
}