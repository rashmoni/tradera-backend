package com.novare.traderabackend.Service;

import com.novare.traderabackend.Entities.AuctionItem;
import com.novare.traderabackend.Repository.AuctionRepo;
import com.novare.traderabackend.Utils.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AuctionService {
    @Autowired
    AuctionRepo auctionRepo;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    private final IStorageService iStorageService;

    public AuctionService(IStorageService iStorageService) {
        this.iStorageService = iStorageService;
    }

    public List<AuctionItem> getAllItems() {
        List<AuctionItem> allAuctionsItem =  auctionRepo.findAll();
        return allAuctionsItem;
    }

    public void postNewAuction(AuctionItem auctionItem) {
        String message;

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

        auctionRepo.save(auctionItem);
        message = "AuctionItem is save in database";
        log.info(message);
    }

    public List<AuctionItem> getByItemName(String name) {
        return auctionRepo.findAuctionByName(name);

    }

    public Optional<AuctionItem> getOneItem(Long id) {
        return auctionRepo.findById(id);
    }
}
