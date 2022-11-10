package com.novare.traderabackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "auction_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String item_name;
    private String description;
    private Long owner_id;
    private String item_image_url;
    private Double initial_price;
    // We can set this with the current time when creating an item.
    private Date start_date;
    private Date stop_date;
}
