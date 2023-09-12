package com.example.learnSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** アカウントコントローラ */
@Controller
@RequestMapping("/LearnSecurity")
public class AccountController {
	/** マイページ　*/
	@GetMapping("/Mypage")
	public String Mypage() {
		return "Main";
	}
	
	/** アカウント登録ページ　*/
	@GetMapping("/Entry")
	public String AccountEntryView() {
		return "accountRegister";
	}
}