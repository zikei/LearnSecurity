package com.example.learnSecurity.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.entity.Lesson;
import com.example.learnSecurity.repository.LessonRepository;

/** 実習関連処理実装クラス　*/
@Service
@Transactional
public class LessonServiceImpl  implements LessonService{
	@Autowired
	LessonRepository lessonRepo;
	
	@Override
	public List<Lesson> selectAllPractice() {
		List<Lesson> lessonList = new ArrayList<>();
		lessonRepo.findAll().forEach(lessonList::add);
		
		return lessonList;
	}
}