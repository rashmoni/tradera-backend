package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Repository.TraderRepo;
import com.novare.traderabackend.Service.TraderService;
import com.novare.traderabackend.Utils.PasswordEncryptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
@Log4j2
public class TraderController {
    @Autowired
    TraderRepo traderRepo;
    @Autowired
    TraderService traderService;

    @PostMapping("/signup")
    public ResponseEntity<Trader> register(@RequestBody Trader trader) {
        // TODO: Add check for duplicate email
        Trader alreadyRegistered = traderRepo.findByEmail(trader.getEmail());
        if(alreadyRegistered != null) {
            log.info("Duplicate email: " + trader.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Trader());
        }

        String hashingSalt = null;
        try {
            hashingSalt = PasswordEncryptor.getSalt();
            trader.setHashingSalt(hashingSalt);
            trader.setPassword(PasswordEncryptor.get_SHA_256_securePassword(trader.getPassword(), hashingSalt));
        } catch (NoSuchAlgorithmException e) {
        }

        traderRepo.save(trader);
        log.info("User created: " + trader.getEmail() + " " + trader.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(trader);
    }

    @PostMapping("/signin")
    public ResponseEntity<Trader> login(@RequestBody Trader trader) {

        Trader loggedInTrader = traderRepo.findByEmail(trader.getEmail());
        Long id = Long.valueOf(0);
        Trader failedLogin = new Trader(id, "", "", "", "");
        // If email not found return failed login.
        if (loggedInTrader == null) {
            log.info("User not found: " + trader.getEmail());
            return ResponseEntity.ok(failedLogin);
        }

        String hashingSalt = loggedInTrader.getHashingSalt();
        String password = null;
        try {
            password = PasswordEncryptor.get_SHA_256_securePassword(trader.getPassword(), hashingSalt);
        } catch (NoSuchAlgorithmException e) {}

        if (!loggedInTrader.getPassword().equals(password)) {
            log.warn("Failed sign in: " + trader.getEmail() + " " + trader.getPassword());
            return ResponseEntity.ok(failedLogin);
        }

        log.info("User signed in: " + trader.getEmail());
        return ResponseEntity.ok(loggedInTrader);
    }
}
