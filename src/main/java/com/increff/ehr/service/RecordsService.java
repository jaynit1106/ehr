package com.increff.ehr.service;

import com.increff.ehr.dao.FileDao;
import com.increff.ehr.dao.RecordsDao;
import com.increff.ehr.model.form.RecordsForm;
import com.increff.ehr.pojo.FilePojo;
import com.increff.ehr.pojo.RecordsPojo;
import com.increff.ehr.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = ApiException.class)
public class RecordsService {

    @Autowired
    private RecordsDao recordsDao;
    @Autowired
    private FileDao fileDao;
    @Value("${app.key}")
    private String appKey;

    @Autowired
    private BlowfishService blowfishService;

    public void addRecords(RecordsPojo recordsPojo){
        recordsDao.insert(recordsPojo);
    }

    public List<RecordsPojo> getAll(){
        return recordsDao.getALlEmpty();
    }

    public RecordsPojo getById(int id) throws ApiException {
        RecordsPojo p = recordsDao.getById(id, RecordsPojo.class);
        RecordsPojo newPojo = new RecordsPojo();
        try {
            newPojo.setPdf(blowfishService.decrypt(p.getPdf(), appKey));
            newPojo.setTest(p.getTest());
            newPojo.setUser(p.getUser());
        }catch (Exception e){
            throw new ApiException("Decryption problem");
        }
        return newPojo;
    }

    public void update(int id,RecordsPojo  recordsPojo) throws NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        RecordsPojo ex = recordsDao.getById(id,RecordsPojo.class);
        ex.setPdf(blowfishService.encrypt(recordsPojo.getPdf(),appKey));
        FilePojo filePojo = fileDao.getById(ex.getFile_id(), FilePojo.class);
        filePojo.setStatus("reports uploaded");
    }

    public List<RecordsPojo> getAllByFile(int file) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        List<RecordsPojo> recordsPojoList = recordsDao.getAllByFile(file);
        List<RecordsPojo> finalList = new ArrayList<>();
        for(RecordsPojo recordsPojo:recordsPojoList){
            RecordsPojo temp = new RecordsPojo();
            temp.setFile_id(recordsPojo.getFile_id());
            temp.setPdf(blowfishService.decrypt(recordsPojo.getPdf(),appKey));
            temp.setId(recordsPojo.getId());
            temp.setUser(recordsPojo.getUser());
            temp.setTest(recordsPojo.getTest());
            finalList.add(temp);
        }
        return finalList;
    }
}
