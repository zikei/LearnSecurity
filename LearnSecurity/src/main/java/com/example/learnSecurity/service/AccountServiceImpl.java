package com.example.learnSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.learnSecurity.data.LoginInfo;
import com.example.learnSecurity.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	@Autowired
	LoginInfo loginInfo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Override
	public Integer selectLoginUserId() {
		return loginInfo.getUserId();
	}

	@Override
	public boolean isLoginUser(Integer userId) {
		if(userId == null) return false;
		return loginInfo.getUserId() == userId;
	}

}
