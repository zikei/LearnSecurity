package com.example.learnSecurity.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 仮 アカウント関連処理実装クラス　*/
@Service
@Transactional
public class AccountServiceImplTmp implements AccountService{
	@Override
	public Integer selectLoginUserId() {
		return 1;
	}
}