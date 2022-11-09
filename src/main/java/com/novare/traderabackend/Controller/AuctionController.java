package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Service.AuctionService;
import com.novare.traderabackend.Service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    AuctionService auctionService;
    @Autowired
    AuctionRepo auctionRepo;

    private final IStorageService iStorageService;

    public AuctionController(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }

//    @GetMapping("/auctions")
    @GetMapping
    public List<AuctionItem> getAllItems() {
        List<AuctionItem> AllAuctions = auctionService.getAllItems();
        return AllAuctions;
    }


//    @PostMapping("/auctions/create")
    @PostMapping("create")
    public ResponseEntity<Boolean> postNewAuction(@RequestBody AuctionItem auctionItem) {

        if(auctionItem.getItem_image_url() != null) {
            String imageURL = UploadFilesController.uploadFile(auctionItem.getItem_image_url());
            auctionItem.setItem_image_url(imageURL);
        }

        auctionService.postNewAuction(auctionItem);

        return ResponseEntity.ok(true);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Optional<AuctionItem> getOneItem(@PathVariable Long id) {
        return auctionRepo.findById(id);
    }

    @GetMapping
    @RequestMapping("search/{searchTerm}")
    public List<AuctionItem> search(@PathVariable String searchTerm) {
        return auctionService.search(searchTerm);
    }
}
