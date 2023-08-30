package com.example.learnSecurity.service;

/** アカウント関連処理 */
public interface AccountService {
	/** ログインユーザId取得 */
	Integer selectLoginUserId();
	
	/** ログインユーザか判定 */
	boolean isLoginUser(Integer userId);
}