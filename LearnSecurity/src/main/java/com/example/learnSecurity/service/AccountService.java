package com.example.learnSecurity.service;

import com.example.learnSecurity.entity.Account;

/** アカウント関連処理 */
public interface AccountService {
	/** ログインユーザId取得 */
	Integer selectLoginUserId();
	
	/** ログインユーザか判定 */
	boolean isLoginUser(Integer userId);
	
	/** ユーザの登録 */
	void userRegist(Account entry);
}