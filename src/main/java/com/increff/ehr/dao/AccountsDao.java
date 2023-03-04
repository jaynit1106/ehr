package com.increff.ehr.dao;

import com.increff.ehr.pojo.AccountsPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AccountsDao extends AbstractDao{

    private static String GET_ALL_BY_USER = "select p from AccountsPojo p where user_id=:user_id";

    public List<AccountsPojo> getAllByUserId(Integer user_id){
        TypedQuery<AccountsPojo> query = getQuery(GET_ALL_BY_USER,AccountsPojo.class);
        query.setParameter("user_id",user_id);
        return query.getResultList();
    }
}
