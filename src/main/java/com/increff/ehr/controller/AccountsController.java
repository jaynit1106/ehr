package com.increff.ehr.controller;

import com.increff.ehr.model.UserData;
import com.increff.ehr.model.data.AccountsData;
import com.increff.ehr.model.form.AccountsForm;
import com.increff.ehr.model.form.DrugsData;
import com.increff.ehr.pojo.AccountsPojo;
import com.increff.ehr.pojo.UserPojo;
import com.increff.ehr.service.AccountsService;
import com.increff.ehr.service.ApiException;
import com.increff.ehr.service.UserService;
import com.increff.ehr.util.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api
@CrossOrigin(origins = "http://localhost:4200")
public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "add accounts")
    @PostMapping(path = "/api/accounts")
    public void addAccounts(@RequestBody AccountsForm accountsForm){
        AccountsPojo accountsPojo = ConvertUtil.objectMapper(accountsForm, AccountsPojo.class);
        accountsService.addAccounts(accountsPojo);
    }

    @ApiOperation(value = "add accounts")
    @PutMapping(path = "/api/accounts/{id}")
    public void updateAccounts(@PathVariable int id){
        accountsService.update(id);
    }

    @ApiOperation(value = "get all accounts")
    @GetMapping(path = "/api/accounts")
    public List<AccountsPojo> getAllAccounts() throws ApiException {

//        List<AccountsPojo> accountsPojoList = accountsService.getAll();
//        List<AccountsData> accountsDataList = new ArrayList<>();
//
//        for(AccountsPojo accountsPojo : accountsPojoList){
//            AccountsData accountsData = new AccountsData();
//            accountsData.setBillingDate(accountsPojo.getBilling_date());
//            accountsData.setCost(accountsPojo.getCost());
//            accountsData.setStatus(accountsPojo.getStatus());
////            String name = userService.getById(accountsPojo.getUser_id()).getEmail();
//            accountsData.setUser_id(accountsPojo.getUser_id());
////            accountsData.setName(name);
//            accountsDataList.add(accountsData);
//        }

        return accountsService.getAll();
    }

    @ApiOperation(value = "get all accounts by user")
    @GetMapping(path = "/api/accounts/{user}")
    public List<AccountsData> getAllAccountsByUser(@PathVariable String user) throws ApiException {

        UserPojo userData = userService.get(user);
        List<AccountsPojo> accountsPojoList = accountsService.getAllByUserId(userData.getId());
        List<AccountsData> accountsDataList = new ArrayList<>();
        for(AccountsPojo accountsPojo : accountsPojoList){
            AccountsData accountsData = new AccountsData();
            accountsData.setBillingDate(accountsPojo.getBillingDate());
            accountsData.setCost(accountsPojo.getCost());
            accountsData.setStatus(accountsPojo.getStatus());
            accountsData.setUser_id(accountsPojo.getUser_id());

            accountsDataList.add(accountsData);
        }
        return accountsDataList;
    }



}
