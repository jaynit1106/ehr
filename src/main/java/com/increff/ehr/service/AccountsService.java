package com.increff.ehr.service;


import com.increff.ehr.dao.AccountsDao;
import com.increff.ehr.pojo.AccountsPojo;
import com.increff.ehr.pojo.DrugsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountsService {

    @Autowired
    private AccountsDao accountsDao;

    public void addAccounts(AccountsPojo accountsPojo){
        accountsDao.insert(accountsPojo);
    }

    public void update(int id){
        AccountsPojo ex = accountsDao.getById(id,AccountsPojo.class);
        ex.setStatus("paid");
    }

    public List<AccountsPojo> getAll(){
        return accountsDao.getAll(AccountsPojo.class);
    }

    public List<AccountsPojo> getAllByUserId(Integer user_id){return accountsDao.getAllByUserId(user_id);}
}
