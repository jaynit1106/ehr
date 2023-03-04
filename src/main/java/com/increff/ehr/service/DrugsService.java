package com.increff.ehr.service;

import com.increff.ehr.dao.DrugsDao;
import com.increff.ehr.model.data.DrugsForm;
import com.increff.ehr.model.form.RecordsForm;
import com.increff.ehr.pojo.DrugsPojo;
import com.increff.ehr.pojo.RecordsPojo;
import com.increff.ehr.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional(rollbackOn = ApiException.class)
public class DrugsService {
    @Autowired
    private DrugsDao drugsDao;

    public void addDrugs(DrugsPojo drugsPojo){
        drugsDao.insert(drugsPojo);
    }

    public List<DrugsPojo> getAll(){
        return drugsDao.getAll(DrugsPojo.class);
    }
}
