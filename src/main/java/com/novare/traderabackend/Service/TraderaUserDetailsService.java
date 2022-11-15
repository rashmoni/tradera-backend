package com.novare.traderabackend.Service;
/*
import com.novare.traderabackend.Entities.TraderaUserDetails;
import com.novare.traderabackend.Entities.Trader;
import com.novare.traderabackend.Repository.TraderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraderaUserDetailsService implements UserDetailsService {

    @Autowired
    TraderRepo traderRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Trader> trader = traderRepository.findTraderByEmail(userName);

        trader.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return trader.map(TraderaUserDetails::new).get();
    }


}

 */
