package com.increff.ehr.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
@Getter@Setter
public class AccountsPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Double cost;
    private String status;
    private ZonedDateTime billing_date;

    @PrePersist
    public void timestamp(){
        this.billing_date = ZonedDateTime.now();
    }

}
