package com.example.learnSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 実習コントローラ */
@Controller
@RequestMapping("/LearnSecurity/Practice")
public class PracticeController {
	/** 作成実習インスタンス一覧ページ　*/
	@GetMapping("/List")
	public String PracticeListView(Model model) {
		
		return "InstanceList";
	}
}
