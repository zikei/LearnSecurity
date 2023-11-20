package com.example.learnSecurity.service;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.LessonView;
import com.example.learnSecurity.data.link.PracticeLinkView;
import com.example.learnSecurity.entity.Lesson;
import com.example.learnSecurity.exception.NotFoundException;
import com.example.learnSecurity.repository.LessonRepository;

/** 実習関連処理実装クラス　*/
@Service
@Transactional
public class LessonServiceImpl  implements LessonService{
	@Autowired
	LessonRepository lessonRepo;
	
	@Autowired
	PracticeLessonService plService;
	
	@Autowired
	ConversionToHTMLService toHtmlService;
	
	@Override
	public List<Lesson> selectAllLesson() {
		List<Lesson> lessonList = new ArrayList<>();
		lessonRepo.findAll().forEach(lessonList::add);
		
		return lessonList;
	}
	
	@Override
	public List<Lesson> searchLesson(String word) {
		word.replace(' ', '%');
		word = '%' + word + '%';
		
		List<Lesson> lessonList = lessonRepo.findSearchLessonName(word);
		return lessonList;
	}

	@Override
	public LessonView selectLessonView(Integer lessonId) throws NotFoundException {
		Lesson lesson = selectLesson(lessonId);
		String lessonPath = System.getenv("LESSON_DIR") + lesson.getLessonPath();
		String lessonContent = toHtmlService.fileToHTML(Paths.get(lessonPath));
		List<PracticeLinkView> relationPracList = plService.selectRelationPractice(lessonId);
		
		return new LessonView(lesson, lessonContent, relationPracList);
	}
	
	
	/** 講義情報をDBから取得 */
	private Lesson selectLesson(Integer lessonId) throws NotFoundException {
		return lessonRepo.findById(lessonId)
				.orElseThrow(() -> new NotFoundException("404 lessonID : " + lessonId));
	}
}