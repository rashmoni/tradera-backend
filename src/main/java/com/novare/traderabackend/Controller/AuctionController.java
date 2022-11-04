package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @GetMapping("/auctions")
    public List<AuctionItem> getAllItems() {
        return auctionService.getAllItems();
    }

    @PostMapping("/auctions/create")
    public ResponseEntity<Boolean> postNewAuction(@RequestBody AuctionItem auctionItem) {
        auctionService.postNewAuction(auctionItem);
        return ResponseEntity.ok(true);
    }
}
