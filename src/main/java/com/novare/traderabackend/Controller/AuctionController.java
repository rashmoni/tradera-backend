package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Service.AuctionService;
import com.novare.traderabackend.Service.FileSystemStorageService;
import com.novare.traderabackend.Service.IStorageService;
import com.novare.traderabackend.Utils.FileUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
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
        return AllAuctions;
    }

    @PostMapping("/auctions/create")
    public ResponseEntity<Boolean> postNewAuction(@RequestBody AuctionItem auctionItem) {

        if(auctionItem.getItem_image_url() != null) {
            String imageDataBytes = FileUtil.getImageFromBase64(auctionItem.getItem_image_url());
            byte [] decodedBytes = Base64.decodeBase64(imageDataBytes);
            String imageURL = this.fileSystemStorageService.storeBase64(decodedBytes);
            String baseURL = "http://localhost:9000/files/";
            String completeURL = baseURL + imageURL;
            auctionItem.setItem_image_url(completeURL);
        }

        Date today = Date.valueOf(LocalDate.now());
        auctionItem.setStart_date(today);

        auctionService.postNewAuction(auctionItem);

        return ResponseEntity.ok(true);
    }

    @GetMapping
    @RequestMapping("/auctions/{id}")
    public Optional<AuctionItem> getOneItem(@PathVariable Long id) {
        return auctionRepo.findById(id);
    }

    @GetMapping("/auctions/{name}")
    public List<AuctionItem> getByItemName(@PathVariable String name) {
        return auctionService.getByItemName(name);
    }

}
