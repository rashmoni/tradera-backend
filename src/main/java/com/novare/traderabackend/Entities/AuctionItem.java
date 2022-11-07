package com.novare.traderabackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    private int owner_id;
    private String item_image_url;

    private int initial_price;
    // We can set this with the current time when creating an item.
    private Timestamp start_date;

    private Timestamp stop_date;
    /*@OneToOne
    @JoinColumn(name = "owner_id", updatable=false, insertable = false)
    private User owner;*/

}
