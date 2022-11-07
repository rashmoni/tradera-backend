package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Repository.TraderRepo;
import com.novare.traderabackend.Service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AuctionController {

    @Autowired
    AuctionService auctionService;
    @Autowired
    AuctionRepo auctionRepo;
    @Autowired
    TraderRepo traderRepo;

    @GetMapping("/auctions")
    public List<AuctionItem> getAllItems() {
        List<AuctionItem> AllAuctions = auctionService.getAllItems();
        return AllAuctions;
    }


    @PostMapping("/auctions/create")
    public ResponseEntity<Boolean> postNewAuction(@RequestBody AuctionItem auctionItem) {
        Long ownerId = auctionItem.getOwner_id();
        Trader trader = traderRepo.getReferenceById(ownerId);
        auctionItem.setOwner(trader);

        auctionService.postNewAuction(auctionItem);

        return ResponseEntity.ok(true);
    }

    @GetMapping
    @RequestMapping("/auctions/{id}")
    public Optional<AuctionItem> getOneItem(@PathVariable Long id) {
        return auctionRepo.findById(id);
    }

}
