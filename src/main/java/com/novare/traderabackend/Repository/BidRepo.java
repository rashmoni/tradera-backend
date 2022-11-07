package com.novare.traderabackend.Repository;

import com.novare.traderabackend.Entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {
    List<Bid> findByAuctionItemId(Long auctionItemId);
}
