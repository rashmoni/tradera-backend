package com.novare.traderabackend.Repository;

import com.novare.traderabackend.Entities.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepo extends JpaRepository<Trader, Long> {

}
