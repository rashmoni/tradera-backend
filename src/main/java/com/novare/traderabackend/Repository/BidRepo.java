package com.novare.traderabackend.Repository;

import com.novare.traderabackend.Entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {
}
