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
import com.example.learnSecurity.data.PageView;
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
	
	@Autowired
	PageView<LessonLinkView> lessonPage;
	
/* ====================================================================================================================== */
	
	/** pの値がない場合：講義一覧取得
	 * pの値がある場合はそのページを表示 */
	@GetMapping
	public String lessonListView(Model model, @RequestParam(required = false) Integer p) {
		if(p != null) return lesssonView(model, p);
		List<Lesson> lessonList = lessonService.selectAllLesson();
		makeLessonPage(lessonList);
		
		return "redirect:/LearnSecurity/Lesson?p=1";
	}
	
	/** 講義検索　*/
	@PostMapping
	public String lessonSearch(@RequestParam String sword, Model model) {
		List<Lesson> lessonList = lessonService.searchLesson(sword);
		makeLessonPage(lessonList);
		
		return "redirect:/LearnSecurity/Lesson?p=1";
	}
	
	/** 講義表示 */
	public String lesssonView(Model model, Integer page) {
		if(page==null) return "redirect:/LearnSecurity/Lesson";
		if(lessonPage==null) return "redirect:/LearnSecurity/Lesson";
		try {
			lessonPage.setPageNum(page);
		} catch (NotFoundException e) {
			 return "redirect:/LearnSecurity/Lesson";
		}
		
		model.addAttribute("LessonList", lessonPage.getPageList());
		model.addAttribute("page", lessonPage);
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
	/** 講義ページ作成 */
	private void makeLessonPage(List<Lesson> lessonList) {
		List<LessonLinkView> lessonLinkList = makeLessonLinkViewList(lessonList);
		lessonPage = new PageView<LessonLinkView>(lessonLinkList);
	}
	
	/** 講義リストをLessonLinkViewListに変換 */
	private List<LessonLinkView> makeLessonLinkViewList(List<Lesson> LessonList){
		List<LessonLinkView> lessonLinkList = new ArrayList<>();
		LessonList.forEach(lesson -> lessonLinkList.add(new LessonLinkView(lesson)));
		
		return lessonLinkList;
	}
}