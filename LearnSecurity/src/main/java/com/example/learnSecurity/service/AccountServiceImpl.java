package com.example.learnSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.learnSecurity.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepo;
	
	@Override
	public Integer selectLoginUserId() {
		return 1;
	}

	@Override
	public boolean isLoginUser(Integer userId) {
		return true;
	}

}
