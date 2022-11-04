package com.novare.traderabackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auction_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String item_name;
    private String description;
    //@Column(nullable = true)
    private int owner_id;
    private String item_image_url;
    private int initial_price;
    private String start_date;
    private Date stop_date;
    /*@OneToOne
    @JoinColumn(name = "owner_id", updatable=false, insertable = false)
    private User owner;*/

}
