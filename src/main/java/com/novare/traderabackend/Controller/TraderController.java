package com.novare.traderabackend.Controller;

import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Service.TraderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
@Log4j2
public class TraderController {
    @Autowired
    TraderService traderService;
    @Autowired
    PasswordEncoder passwordEncoder;

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

            log.info("User created " + data.getEmail() + " " + data.getPassword());
            return data.getName()+"  "+"Successfully created";
        }catch (Exception e){
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

}
