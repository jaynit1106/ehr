package com.increff.ehr.pojo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(
        name = "drugs",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name","dose"})
)
public class DrugsPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String dose;
    private String supplier;
    private Double MRP;


}
