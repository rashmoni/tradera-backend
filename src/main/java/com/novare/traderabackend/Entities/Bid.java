package com.novare.traderabackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name ="bids")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long traderId;
    private Long auctionItemId;
    private Integer amount;
    private Timestamp creationTime;
}
