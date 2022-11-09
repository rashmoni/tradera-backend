package com.novare.traderabackend.Service;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Repository.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    @Autowired
    AuctionRepo auctionRepo;
    public List<AuctionItem> getAllItems() {
        List<AuctionItem> allAuctionsItem =  auctionRepo.findAll();
        return allAuctionsItem;
    }

    public void postNewAuction(AuctionItem auctionItem) {
        //write the logic to check if the item is already present in DB
        AuctionItem savedAuction = auctionRepo.save(auctionItem);
    }

    public List<AuctionItem> search(String searchTerm) {
        List<AuctionItem> allAuctionItems =  auctionRepo.findAll();
        List<AuctionItem> result = allAuctionItems.stream()
                .filter(auctionItem -> auctionItem.getItem_name().contains(searchTerm))
                .collect(Collectors.toList());
        return result;
    }
}
