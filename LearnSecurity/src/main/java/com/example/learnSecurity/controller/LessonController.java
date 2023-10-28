package com.example.learnSecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.entity.Lesson;
import com.example.learnSecurity.service.LessonService;

/** 講義コントローラ */
@Controller
@RequestMapping("/LearnSecurity/Lesson")
public class LessonController {
	@Autowired
	LessonService lessonService;
	
/* ====================================================================================================================== */
	
	/** 講義一覧ページ　*/
	@GetMapping
	public String PracticeListView(Model model) {
		List<Lesson>         lessonList     = lessonService.selectAllPractice();
		List<LessonLinkView> lessonLinkList = makeLessonLinkViewList(lessonList);
		
		model.addAttribute("LessonList", lessonLinkList);
		
		return "LessonList";
	}
	
/* ====================================================================================================================== */
	
	/** 講義リストをLessonLinkViewListに変換 */
	private List<LessonLinkView> makeLessonLinkViewList(List<Lesson> LessonList){
		List<LessonLinkView> lessonLinkList = new ArrayList<>();
		LessonList.forEach(lesson -> lessonLinkList.add(new LessonLinkView(lesson)));
		
		return lessonLinkList;
	}
}