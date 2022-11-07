package com.novare.traderabackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer owner_id;
    private Integer auction_item_id;
    private Integer amount;
    private Timestamp creation_time;

}
