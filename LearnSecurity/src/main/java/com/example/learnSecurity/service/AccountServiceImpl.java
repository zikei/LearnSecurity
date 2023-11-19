package com.example.learnSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.learnSecurity.data.LoginInfo;
import com.example.learnSecurity.entity.Account;
import com.example.learnSecurity.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	@Autowired
	LoginInfo loginInfo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Integer selectLoginUserId() {
		return loginInfo.getUserId();
	}

	@Override
	public boolean isLoginUser(Integer userId) {
		if(userId == null) return false;
		return loginInfo.getUserId() == userId;
	}

	@Override
	public void userRegist(Account entry) {
		entry.setPassword(passwordEncoder.encode(entry.getPassword()));
		accountRepo.save(entry);
		loginInfo.makeLoginInfo(entry);
	}

}
