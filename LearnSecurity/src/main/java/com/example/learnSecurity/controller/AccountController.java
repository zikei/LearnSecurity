package com.example.learnSecurity.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learnSecurity.entity.Account;
import com.example.learnSecurity.form.AccountEntryForm;
import com.example.learnSecurity.service.AccountService;
import com.example.learnSecurity.validator.AccountEntryValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

/** アカウントコントローラ */
@Controller
@RequestMapping("/LearnSecurity")
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountEntryValidator accountEntryValidator;
	
	/** チェック登録 */
	@InitBinder("accountEntryForm")
	public void initEntryFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(accountEntryValidator);
	}
	
	/** ユーザ登録フォームの初期化 */
	@ModelAttribute
	public AccountEntryForm setUpAccountEntryForm() {
		return new AccountEntryForm();
	}
	
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
	
	/** ユーザ登録処理　*/
	@PostMapping("/Entry")
	public String AccountRegister(@Validated AccountEntryForm entryForm, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			return AccountEntryView();
		}
		
		Account entryUser = new Account(null, entryForm.getUserName(), entryForm.getPassword(), new Date());
		accountService.userRegist(entryUser);
		
		login(entryForm.getUserName(), entryForm.getPassword(), request);
		
		return "redirect:/LearnSecurity/Mypage";
	}
	
	/** 自動ログイン */
	private void login(String userName, String pass, HttpServletRequest request) {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		if (authentication instanceof AnonymousAuthenticationToken == false) {
			SecurityContextHolder.clearContext();
		}
		        
		try {
			request.login(userName, pass);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}