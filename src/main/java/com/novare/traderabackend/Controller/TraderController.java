package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Repository.TraderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TraderController {
    @Autowired
    private TraderRepo traderRepo;

    @PostMapping("/traders/create")
    public ResponseEntity<Trader> createTraders(@RequestBody Trader trader) {

        return new ResponseEntity<>(traderRepo.saveAndFlush(trader), HttpStatus.CREATED);
    }
}
