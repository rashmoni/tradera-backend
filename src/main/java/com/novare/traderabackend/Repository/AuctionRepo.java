package com.novare.traderabackend.Repository;

import com.novare.traderabackend.Entities.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepo extends JpaRepository<AuctionItem, Long> {

}