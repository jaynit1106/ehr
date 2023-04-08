package com.increff.ehr.controller;

import com.increff.ehr.model.data.FileData;
import com.increff.ehr.model.data.FileData;
import com.increff.ehr.model.form.AccountsForm;
import com.increff.ehr.model.form.FileForm;
import com.increff.ehr.pojo.FilePojo;
import com.increff.ehr.pojo.FilePojo;
import com.increff.ehr.pojo.FilePojo;
import com.increff.ehr.pojo.UserPojo;
import com.increff.ehr.service.ApiException;
import com.increff.ehr.service.FileService;
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
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "add a case file")
    @PostMapping(path = "/api/files")
    public void addFiles(@RequestBody FileForm fileForm) throws ApiException {
        FilePojo filePojo = ConvertUtil.objectMapper(fileForm, FilePojo.class);
        filePojo.setUser_id(userService.get(fileForm.getUser_name()).getId());
        filePojo.setDoctor_id(userService.get(fileForm.getDoctor_name()).getId());
        fileService.insert(filePojo);
    }

    @GetMapping(path = "/api/files/user/{id}")
    public FilePojo getById(@PathVariable int id){
        return fileService.getById(id);
    }

    @ApiOperation(value = "get all files")
    @GetMapping(path = "/api/files")
    public List<FileData> getAllFiles() throws ApiException {
        
        List<FilePojo> FilePojoList = fileService.getAll();
        List<FileData> fielDataList = new ArrayList<>();

        for(FilePojo FilePojo : FilePojoList){
            FileData fileData = new FileData();
            fileData = ConvertUtil.objectMapper(FilePojo, FileData.class);
            fileData.setUser_name(userService.getById(FilePojo.getUser_id()).getEmail());
            fileData.setDoctor_name(userService.getById(FilePojo.getDoctor_id()).getEmail());
            fileData.setId(FilePojo.getId());
            fielDataList.add(fileData);

        }
        
        return fielDataList;
    }

    @ApiOperation(value = "get all files by user")
    @GetMapping(path = "/api/files/{user}")
    public List<FileData> getAllAccountsByUser(@PathVariable String user) throws ApiException {
        UserPojo userData = userService.get(user);
        List<FilePojo> FilePojoList = fileService.getAllByUser(userData.getId());
        List<FileData> fileDataList = new ArrayList<>();
        for(FilePojo FilePojo : FilePojoList){
            FileData fileData = new FileData();
            fileData = ConvertUtil.objectMapper(FilePojo, FileData.class);
            fileData.setUser_name(userService.getById(FilePojo.getUser_id()).getEmail());
            fileData.setDoctor_name(userService.getById(FilePojo.getDoctor_id()).getEmail());
            fileDataList.add(fileData);
        }
        return fileDataList;
    }

    @ApiOperation(value = "get all files by doctor")
    @GetMapping(path = "/api/files/doctor/{doctor}")
    public List<FileData> getAllAccountsByDoctor(@PathVariable String doctor) throws ApiException {
        UserPojo userData = userService.get(doctor);
        List<FilePojo> FilePojoList = fileService.getAllByDoctor(userData.getId());
        List<FileData> fileDataList = new ArrayList<>();
        for(FilePojo FilePojo : FilePojoList){
            FileData fileData = new FileData();
            fileData = ConvertUtil.objectMapper(FilePojo, FileData.class);
            fileData.setUser_name(userService.getById(FilePojo.getUser_id()).getEmail());
            fileData.setDoctor_name(userService.getById(FilePojo.getDoctor_id()).getEmail());
            fileDataList.add(fileData);
        }
        return fileDataList;
    }
}
