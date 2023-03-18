package com.increff.ehr.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "filepojo"
)
public class FilePojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private int doctor_id;
    private String name;
    private String phone_number;
    private String address;
    private String gender;
    private int age;
    private String remarks;
    private String status;

}
