package com.novare.traderabackend.Repository;

import com.novare.traderabackend.Entities.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderRepo extends JpaRepository<Trader, Long> {
//    @Query("SELECT t FROM Trader t WHERE t.email=?1")
 //   Optional<Trader> findTraderByEmail(String email);

    Trader findByEmail(String email);
}
