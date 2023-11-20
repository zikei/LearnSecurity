package com.example.learnSecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.learnSecurity.data.LessonView;
import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.entity.Lesson;
import com.example.learnSecurity.exception.NotFoundException;
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
	public String lessonListView(Model model) {
		List<Lesson>         lessonList     = lessonService.selectAllLesson();
		List<LessonLinkView> lessonLinkList = makeLessonLinkViewList(lessonList);
		
		model.addAttribute("LessonList", lessonLinkList);
		
		return "LessonList";
	}
	
	/** 講義検索　*/
	@PostMapping
	public String lessonSearch(@RequestParam String sword, Model model) {
		System.out.println("1"+sword);
		List<Lesson>         lessonList     = lessonService.searchLesson(sword);
		System.out.println("2");
		List<LessonLinkView> lessonLinkList = makeLessonLinkViewList(lessonList);
		System.out.println("3");
		
		model.addAttribute("LessonList", lessonLinkList);
		System.out.println("4");
		return "LessonList";
	}
	
	/** 講義ページ */
	@GetMapping("/{LessonId}")
	public String lessonView(@PathVariable Integer LessonId, Model model) {
		LessonView lesson;
		try {
			lesson = lessonService.selectLessonView(LessonId);
		} catch (NotFoundException e) {
			model.addAttribute("errorMsg", "講義情報を取得できませんでした");
			return "error";
		}
		
		model.addAttribute("lesson", lesson);
		
		return "LessonStudy";
	}
	
/* ====================================================================================================================== */
	
	/** 講義リストをLessonLinkViewListに変換 */
	private List<LessonLinkView> makeLessonLinkViewList(List<Lesson> LessonList){
		List<LessonLinkView> lessonLinkList = new ArrayList<>();
		LessonList.forEach(lesson -> lessonLinkList.add(new LessonLinkView(lesson)));
		
		return lessonLinkList;
	}
}