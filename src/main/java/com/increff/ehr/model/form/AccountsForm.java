package com.increff.ehr.model.form;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter@Setter
public class AccountsForm {
    private String user_id;
    private Double cost;
    private String status;
    private int file_id;
}
