package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Bid;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Repository.BidRepo;
import com.novare.traderabackend.Repository.TraderRepo;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bids")
@Log4j2
public class BidController {
    @Autowired
    private BidRepo bidRepo;

    @Autowired
    private AuctionRepo auctionRepo;

    @GetMapping
    public List<Bid> list() {
        String message = "User tried to access /bids endpoint";
        log.info(message);
        return bidRepo.findAll(); }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bid> create(@RequestBody final Bid bid){

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        bid.setCreationTime(currentTime);
        String message = "User tried to access /bids/create endpoint with traderId: " + bid.getTraderId();
        log.info(message);

        return new ResponseEntity<>(bidRepo.saveAndFlush(bid), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("{auctionItemId}")
    public List<Bid>getBidsByAuctionItemId(@PathVariable Long auctionItemId) {
        String message = "User tried to access /bids/auctionItemId endpoint with data: "+ auctionItemId;
        log.info(message);
        return bidRepo.findByAuctionItemId(auctionItemId);
    }
}
