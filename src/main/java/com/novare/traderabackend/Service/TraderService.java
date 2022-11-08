package com.novare.traderabackend.Service;


import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Repository.TraderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TraderService {
    @Autowired
    TraderRepo traderRepo;
    public void saveTrader(Trader trader) {

        Optional<Trader> traderOptional = traderRepo.findTraderByEmail(trader.getEmail());
        if (traderOptional.isPresent()) {
            throw new IllegalStateException("email already exixts!");
        }
        traderRepo.save(trader);
    }
}
