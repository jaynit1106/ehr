package com.increff.ehr.pojo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class RecordsPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String user;

    private  String test;

    @Column(length = 10000)
    private String pdf;

}
