package com.increff.ehr.model.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugsForm {
    private String name;
    private String dose;
    private String supplier;
    private Double MRP;
}
