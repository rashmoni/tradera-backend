package com.novare.traderabackend.Service;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Repository.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuctionService {
    @Autowired
    AuctionRepo auctionRepo;
    public List<AuctionItem> getAllItems() {
        return auctionRepo.findAll();
    }

    public void postNewAuction(AuctionItem auctionItem) {
        AuctionItem savedAuction = auctionRepo.save(auctionItem);
    }
}
