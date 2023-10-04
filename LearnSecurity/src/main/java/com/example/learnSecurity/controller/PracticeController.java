package com.example.learnSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learnSecurity.service.AccountService;
import com.example.learnSecurity.service.PracticeService;

/** 実習コントローラ */
@Controller
@RequestMapping("/LearnSecurity/Practice")
public class PracticeController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	PracticeService practiceService;
	
/* ====================================================================================================================== */
	
	/** 実習一覧ページ　*/
	@GetMapping
	public String PracticeListView(Model model) {
		return "PracticeList";
	}
}
