package com.increff.ehr.model.data;

import com.increff.ehr.model.form.AccountsForm;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;


@Getter@Setter
public class AccountsData extends AccountsForm {

    private ZonedDateTime billingDate;

    private String name;
}
