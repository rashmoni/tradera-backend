package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Service.AuctionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
@Log4j2
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @GetMapping("/auctions")
    public List<AuctionItem> getAllItems() {
        List<AuctionItem> AllAuctions = auctionService.getAllItems();
        String message = "User tried to access /auctions endpoint";
        log.info(message);
        return AllAuctions;
    }

    @PostMapping("/auctions/create")
    public String postNewAuction(@RequestBody AuctionItem auctionItem) {
        String message = "User tried to create new item witth name: " +  auctionItem.getItem_name() ;
        log.info(message);

        auctionService.postNewAuction(auctionItem);
        return (auctionItem.getItem_name() + " is successfully created");
    }

    @GetMapping
    @RequestMapping("/auctions/{id}")
    public Optional<AuctionItem> getOneItem(@PathVariable Long id) {

        String message = "User tried to access /auctions/id endpoint with id: "+id;
        log.info(message);

        return auctionService.getOneItem(id);
    }

    @GetMapping("/auctions/search/{name}")
    public List<AuctionItem> getByItemName(@PathVariable String name) {

        String message = "User tried to search with name: "+ name;
        log.info(message);

        return auctionService.getByItemName(name);
    }

}
