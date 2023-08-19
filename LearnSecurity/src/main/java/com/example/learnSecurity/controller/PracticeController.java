package com.example.learnSecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learnSecurity.data.link.InstanceLinkView;
import com.example.learnSecurity.entity.Instance;
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
	
	/** 作成実習インスタンス一覧ページ　*/
	@GetMapping("/List")
	public String PracticeListView(Model model) {
		Integer LoginuserId = accountService.selectLoginUserId();
		
		List<Instance> insList = practiceService.selectInstanceByUserId(LoginuserId);
		List<InstanceLinkView> insLinkList = makeInstanceLinkViewList(insList);
		
		model.addAttribute("insList", insLinkList);
		
		return "InstanceList";
	}
	
/* ====================================================================================================================== */
	
	/** インスタンスリストをInstanceLinkViewListに変換 */
	private List<InstanceLinkView> makeInstanceLinkViewList(List<Instance> insList){
		List<InstanceLinkView> insLinkList = new ArrayList<>();
		insList.forEach(ins -> insLinkList.add(new InstanceLinkView(ins)));
		
		return insLinkList;
	}
}
