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
    private String user_id;
    private Double cost;
    private String status;
    private ZonedDateTime billingDate;
    private int file_id;

    @PrePersist
    public void timestamp(){
        this.billingDate = ZonedDateTime.now();
    }

}
