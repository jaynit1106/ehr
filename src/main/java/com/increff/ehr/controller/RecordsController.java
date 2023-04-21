package com.increff.ehr.controller;

import com.increff.ehr.model.data.RecordsData;
import com.increff.ehr.model.form.RecordsForm;
import com.increff.ehr.pojo.RecordsPojo;
import com.increff.ehr.pojo.UserPojo;
import com.increff.ehr.service.ApiException;
import com.increff.ehr.service.RecordsService;
import com.increff.ehr.service.UserService;
import com.increff.ehr.util.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Api
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecordsController {

    @Autowired
    private RecordsService recordsService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "adds patient records")
    @PostMapping(path = "/api/records")
    public void addRecords(@RequestBody RecordsPojo recordsPojo){
        recordsService.addRecords(recordsPojo);
    }

    @ApiOperation(value = "get all records")
    @GetMapping(path = "/api/records")
    public List<RecordsPojo> getAllRecords(){
        return recordsService.getAll();
    }

    @ApiOperation(value = "get all records")
    @GetMapping(path = "/api/records/{id}")
    public RecordsPojo getById(@PathVariable int id) throws ApiException {
        return recordsService.getById(id);
    }

    @ApiOperation(value = "update records")
    @PutMapping(path = "/api/records/{id}")
    public void updateRecord(@PathVariable int id,@RequestBody RecordsPojo recordsPojo) throws NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        recordsService.update(id,recordsPojo);
    }

    @ApiOperation(value = "get all records")
    @GetMapping(path = "/api/records/file/{id}")
    public List<RecordsPojo> getByFileId(@PathVariable String id) throws ApiException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        List<String> temp = Arrays.asList(id.split(","));
        String user = temp.get(1);
        int ids = Integer.parseInt(String.valueOf(temp.get(0)));
        UserPojo userPojo = userService.get(user);
        System.out.println("hello");
        List<RecordsPojo> recordsPojoList = recordsService.getAllByFile(ids);
        System.out.println(userPojo.getId());
        if(Objects.equals(String.valueOf(userPojo.getId()),recordsPojoList.get(0).getUser()) || Objects.equals("doctor",userPojo.getRole())){}
        else throw new ApiException("Access Denied");
        System.out.println("jo");
        return recordsPojoList;
    }




}
