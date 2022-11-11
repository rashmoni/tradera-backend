package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Service.AuctionService;
import com.novare.traderabackend.Service.FileSystemStorageService;
import com.novare.traderabackend.Service.IStorageService;
import com.novare.traderabackend.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
@Log4j2
public class AuctionController {

    @Autowired
    AuctionService auctionService;
    @Autowired
    AuctionRepo auctionRepo;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    private final IStorageService iStorageService;

    public AuctionController(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }

    @GetMapping("/auctions")
    public List<AuctionItem> getAllItems() {
        List<AuctionItem> AllAuctions = auctionService.getAllItems();
        String message = "User tried to access /auctions endpoint";
        log.info(message);
        return AllAuctions;
    }

    @PostMapping("/auctions/create")
    public String postNewAuction(@RequestBody AuctionItem auctionItem) {
        String message = "User tried to access /auctions/create endpoint: " +  auctionItem.getItem_name() ;
        log.info(message);

        if(auctionItem.getItem_image_url() != null) {

            message = "trying to convert base64 image and store it to filesystem";
            log.info(message);

            String imageDataBytes = FileUtil.getImageFromBase64(auctionItem.getItem_image_url());
            byte [] decodedBytes = Base64.decodeBase64(imageDataBytes);
            String imageURL = this.fileSystemStorageService.storeBase64(decodedBytes);
            String baseURL = "http://localhost:9000/files/";
            String completeURL = baseURL + imageURL;

            message = "image successfully stored, image url is: "+ completeURL;
            log.info(message);

            auctionItem.setItem_image_url(completeURL);
        }

        Date today = Date.valueOf(LocalDate.now());
        auctionItem.setStart_date(today);

        auctionService.postNewAuction(auctionItem);
        message = "AuctionItem is save in database";
        log.info(message);

        return (auctionItem.getItem_name() + " is successfully created");
    }

    @GetMapping
    @RequestMapping("/auctions/{id}")
    public Optional<AuctionItem> getOneItem(@PathVariable Long id) {

        String message = "User tried to access /auctions/id endpoint with id: "+id;
        log.info(message);

        return auctionRepo.findById(id);
    }

    @GetMapping("/auctions/search/{name}")
    public List<AuctionItem> getByItemName(@PathVariable String name) {

        String message = "User tried to search with name: "+ name;
        log.info(message);

        return auctionService.getByItemName(name);
    }

}
