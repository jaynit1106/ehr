package com.increff.ehr.model.data;

import com.increff.ehr.model.form.FileForm;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Setter
public class FileData extends FileForm{
    int id;
}
