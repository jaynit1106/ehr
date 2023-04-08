package com.increff.ehr.service;

import com.increff.ehr.dao.FileDao;
import com.increff.ehr.pojo.DrugsPojo;
import com.increff.ehr.pojo.FilePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = ApiException.class)
public class FileService {
    @Autowired
    private FileDao fileDao;

    public void insert(FilePojo filePojo){
        fileDao.insert(filePojo);
    }

    public List<FilePojo> getAll(){
        return fileDao.getAll(FilePojo.class);
    }
    public FilePojo getById(int id){
        return fileDao.getById(id, FilePojo.class);
    }

    public List<FilePojo> getAllByUser(int user_id){return fileDao.getAllByUser(user_id);}

    public List<FilePojo> getAllByDoctor(int doctor_id){return fileDao.getAllByDoctor(doctor_id);}
}
