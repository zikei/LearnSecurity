package com.example.learnSecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learnSecurity.data.PracticeView;
import com.example.learnSecurity.data.link.PracticeLinkView;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;
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
		List<Practice>         pracList     = practiceService.selectAllPractice();
		List<PracticeLinkView> pracLinkList = makePracticeLinkViewList(pracList);
		
		model.addAttribute("pracList", pracLinkList);
		
		return "PracticeList";
	}
	
	/** 実習ダウンロードページ */
	@GetMapping("/{pracId}")
	public String PracticeDLView(@PathVariable Integer pracId, Model model) {
		PracticeView prac;
		try {
			prac = practiceService.selectPracticeView(pracId);
		} catch (NotFoundException e) {
			model.addAttribute("errorMsg", "実習情報を取得できませんでした");
			return "error";
		}
		
		model.addAttribute("practice", prac);
		
		return "DLpractice";
	}
	
/* ====================================================================================================================== */
	
	/** 実習リストをPracticeLinkViewListに変換 */
	private List<PracticeLinkView> makePracticeLinkViewList(List<Practice> pracList){
		List<PracticeLinkView> pracLinkList = new ArrayList<>();
		pracList.forEach(prac -> pracLinkList.add(new PracticeLinkView(prac)));
		
		return pracLinkList;
	}
}
