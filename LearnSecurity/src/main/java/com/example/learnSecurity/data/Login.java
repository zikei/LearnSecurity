package com.example.learnSecurity.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.learnSecurity.entity.Account;

/** ログイン用ユーザ情報 */
public class Login extends User{
	public Login(Account user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserId().toString(), user.getPassword(), authorities);
	}
}