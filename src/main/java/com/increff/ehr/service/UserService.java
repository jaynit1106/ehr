package com.increff.ehr.service;
import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;

import com.increff.ehr.dao.UserDao;
import com.increff.ehr.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private BlowfishService blowfishService;

	@Value("${doctor.username}")
	private String doctor;

	@Value("${app.key}")
	private String appKey;

	@Transactional
	public void add(UserPojo p) throws Exception {
		normalize(p);
		UserPojo existing = dao.select(p.getEmail());
		if (existing != null) {
			throw new ApiException("User with given email already exists");
		}
		if(Objects.equals(p.getEmail(),doctor))p.setRole("doctor");
		else p.setRole("patient");


		p.setPassword(blowfishService.encrypt(p.getPassword(),appKey));

		dao.insert(p);
	}



	@Transactional(rollbackOn = ApiException.class)
	public UserPojo get(String email) throws ApiException {
		UserPojo p = dao.select(email);
		try {
			UserPojo newPojo = new UserPojo();
			newPojo.setPassword(blowfishService.decrypt(p.getPassword(),appKey));
			newPojo.setRole(p.getRole());
			newPojo.setId(p.getId());
			newPojo.setEmail(p.getEmail());
			return newPojo;
		}catch (Exception e){
			System.out.println(e);
			throw new ApiException("error in decryption");
		}
	}

	@Transactional(rollbackOn = ApiException.class)
	public UserPojo getById(int id) throws ApiException {
		return dao.select(id);
	}


	@Transactional
	public List<UserPojo> getAll() {
		return dao.selectAll();
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}

	protected static void normalize(UserPojo p) {
		p.setEmail(p.getEmail().toLowerCase().trim());
		p.setRole(p.getRole().toLowerCase().trim());
	}


}
