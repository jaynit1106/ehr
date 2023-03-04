package com.increff.ehr.service;

import com.increff.ehr.dao.RecordsDao;
import com.increff.ehr.model.form.RecordsForm;
import com.increff.ehr.pojo.RecordsPojo;
import com.increff.ehr.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = ApiException.class)
public class RecordsService {

    @Autowired
    private RecordsDao recordsDao;

    public void addRecords(RecordsPojo recordsPojo){
        recordsDao.insert(recordsPojo);
    }

    public List<RecordsPojo> getAll(){
        return recordsDao.getAll(RecordsPojo.class);
    }
}
