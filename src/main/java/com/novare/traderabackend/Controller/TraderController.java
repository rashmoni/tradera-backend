package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Repository.TraderRepo;
import com.novare.traderabackend.Utils.PasswordEncryptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
@Log4j2
public class TraderController {
    @Autowired
    TraderRepo traderRepo;

    @PostMapping("/signup")
    public ResponseEntity<Trader> register(@RequestBody Trader trader) {
        ResponseEntity<Trader> BAD_REQUEST = checkForDuplicateEmail(trader);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        encryptPassword(trader);

        traderRepo.save(trader);

        log.info("User created: " + trader.getEmail() + " " + trader.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(trader);
    }

    private ResponseEntity<Trader> checkForDuplicateEmail(Trader trader) {
        Trader alreadyRegistered = traderRepo.findByEmail(trader.getEmail());
        if (alreadyRegistered != null) {
            log.info("Duplicate email: " + trader.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Trader());
        }
        return null;
    }

    private void encryptPassword(Trader trader) {
        String hashingSalt;
        try {
            hashingSalt = PasswordEncryptor.getSalt();
            trader.setHashingSalt(hashingSalt);
            trader.setPassword(PasswordEncryptor.get_SHA_256_securePassword(trader.getPassword(), hashingSalt));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Trader> login(@RequestBody Trader trader) {
        Trader loggedInTrader = traderRepo.findByEmail(trader.getEmail());

        if (loggedInTrader == null) {
            log.info("User not found: " + trader.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Trader(0L, "", "", "", ""));
        }

        ResponseEntity<Trader> failedLogin = checkPassword(trader, loggedInTrader);

        if (failedLogin != null) return failedLogin;

        log.info("User signed in: " + trader.getEmail());

        return ResponseEntity.ok(loggedInTrader);
    }

    private ResponseEntity<Trader> checkPassword(Trader trader, Trader loggedInTrader) {
        String hashingSalt = loggedInTrader.getHashingSalt();
        String password;

        try {
            password = PasswordEncryptor.get_SHA_256_securePassword(trader.getPassword(), hashingSalt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (!loggedInTrader.getPassword().equals(password)) {
            log.warn("Failed sign in: " + trader.getEmail() + " " + trader.getPassword());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Trader(0L, "", "", "", ""));
        }
        return null;
    }

    @GetMapping
    @RequestMapping("/traders/{id}")
    public Optional<Trader> getTrader(@PathVariable Long id) {

        String message = "User tried to access /traders/id endpoint with id: " + id;
        log.info(message);

        return traderRepo.findById(id);
    }
}
