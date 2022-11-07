package com.novare.traderabackend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.sql.Timestamp;
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
//    @NotBlank(message = "Name is required.")
    private String item_name;
//    @NotBlank(message= "Description is required.")
    private String description;
    //@Column(nullable = true)
    private int owner_id;
    private String item_image_url;
//    @NotBlank(message= "Initial price is required.")
    private int initial_price;
    // We can set this with the current time when creating an item.
    private Date start_date;
//    @NotNull(message= "Ending time is required.")
//    @NotBlank(message= "Ending time is required.")
    private Date stop_date;
    /*@OneToOne
    @JoinColumn(name = "owner_id", updatable=false, insertable = false)
    private User owner;*/

}
