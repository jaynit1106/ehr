package com.increff.ehr.controller;

import com.increff.ehr.model.data.DrugsForm;
import com.increff.ehr.model.form.DrugsData;
import com.increff.ehr.pojo.DrugsPojo;
import com.increff.ehr.service.DrugsService;
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
public class DrugsController {

    @Autowired
    private DrugsService drugsService;

    @ApiOperation(value = "add drug")
    @PostMapping(path = "/api/drugs")
    public void addDrugs(@RequestBody DrugsForm drugsForm){
        DrugsPojo drugsPojo = ConvertUtil.objectMapper(drugsForm,DrugsPojo.class);
        drugsService.addDrugs(drugsPojo);
    }
    @ApiOperation(value = "get all drugs")
    @GetMapping(path = "/api/drugs")
    public List<DrugsData> getAllDrugs(){

        List<DrugsPojo> drugsPojoList = drugsService.getAll();
        List<DrugsData> drugsDataList = new ArrayList<>();

        for(DrugsPojo drugsPojo : drugsPojoList){
            drugsDataList.add(ConvertUtil.objectMapper(drugsPojo,DrugsData.class));
        }
        return drugsDataList;
    }
}
