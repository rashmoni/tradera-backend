package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Service.TraderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TraderController {
    @Autowired
    TraderService traderService;
    @Autowired
    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(TraderController.class);

    @PostMapping("/signup")
    public String singup(@RequestBody Trader data){
        try {
            Trader traders = Trader.builder()
                    .name(data.getName())
                    .email(data.getEmail())
                    .password(passwordEncoder.encode(data.getPassword()))
                    .roles("ROLE_USER")
                    .build();

            traderService.saveTrader(traders);
            logger.info("User created " + data.getEmail() + " " + data.getPassword());
            return data.getName()+"  "+"Successfully created";
        }catch (Exception e){
            logger.error(e.getMessage());
            return e.getMessage();
        }

    }

}
