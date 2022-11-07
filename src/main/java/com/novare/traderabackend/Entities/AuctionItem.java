package com.novare.traderabackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private Integer initial_price;
    // We can set this with the current time when creating an item.
    private Timestamp start_date;

    private Timestamp stop_date;

}
