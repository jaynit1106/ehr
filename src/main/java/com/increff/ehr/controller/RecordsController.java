package com.increff.ehr.controller;

import com.increff.ehr.model.data.RecordsData;
import com.increff.ehr.model.form.RecordsForm;
import com.increff.ehr.pojo.RecordsPojo;
import com.increff.ehr.service.RecordsService;
import com.increff.ehr.util.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api
@RestController
public class RecordsController {

    @Autowired
    private RecordsService recordsService;

    @ApiOperation(value = "adds patient records")
    @PostMapping(path = "/api/records")
    public void addRecords(@RequestBody RecordsForm recordsForm){
        RecordsPojo recordsPojo = ConvertUtil.objectMapper(recordsForm, RecordsPojo.class);
        recordsService.addRecords(recordsPojo);
    }

    @ApiOperation(value = "get all records")
    @GetMapping(path = "/api/records")
    public List<RecordsData> getAllRecords(){
        List<RecordsPojo> recordsPojoList = recordsService.getAll();
        List<RecordsData> recordsDataList = new ArrayList<>();
        for(RecordsPojo recordsPojo:recordsPojoList){
            recordsDataList.add(ConvertUtil.objectMapper(recordsPojo,RecordsData.class));
        }
        return recordsDataList;
    }
}
