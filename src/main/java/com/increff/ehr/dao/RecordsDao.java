package com.increff.ehr.dao;

import com.increff.ehr.pojo.FilePojo;
import com.increff.ehr.pojo.RecordsPojo;
import com.increff.ehr.pojo.UserPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecordsDao extends AbstractDao{
    private static String GET_ALL_BY_FILE = "select p from RecordsPojo p where file_id=:file_id";
    private static String GET_ALL_BY_EMPTY = "select p from RecordsPojo p where pdf=:pdf";

    public List<RecordsPojo> getAllByFile(int file){
        TypedQuery<RecordsPojo> query = getQuery(GET_ALL_BY_FILE,RecordsPojo.class);
        query.setParameter("file_id",file);
        return query.getResultList();
    }

    public List<RecordsPojo> getALlEmpty(){
        TypedQuery<RecordsPojo> query = getQuery(GET_ALL_BY_EMPTY,RecordsPojo.class);
        query.setParameter("pdf","");
        return query.getResultList();
    }

}
