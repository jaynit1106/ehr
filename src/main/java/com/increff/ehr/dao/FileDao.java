package com.increff.ehr.dao;

import com.increff.ehr.pojo.AccountsPojo;
import com.increff.ehr.pojo.FilePojo;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FileDao extends AbstractDao{

    private static String GET_ALL_BY_USER = "select p from FilePojo p where user_id=:user_id";
    private static String GET_ALL_BY_DOCTOR = "select p from FilePojo p where doctor_id=:doctor_id";

    public List<FilePojo> getAllByUser(int user_id){
        TypedQuery<FilePojo> query = getQuery(GET_ALL_BY_USER,FilePojo.class);
        query.setParameter("user_id",user_id);
        return query.getResultList();
    }

    public List<FilePojo> getAllByDoctor(int doctor_id){
        TypedQuery<FilePojo> query = getQuery(GET_ALL_BY_DOCTOR,FilePojo.class);
        query.setParameter("doctor_id",doctor_id);
        return query.getResultList();
    }
}
