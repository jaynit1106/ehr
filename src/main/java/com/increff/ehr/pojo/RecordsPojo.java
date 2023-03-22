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
    private Integer user_id;

    @Column(nullable = false,length = 50000)
    private String pdf;

}
