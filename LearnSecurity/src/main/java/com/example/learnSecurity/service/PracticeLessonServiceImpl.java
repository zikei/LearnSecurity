package com.example.learnSecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.repository.LessonRepository;
import com.example.learnSecurity.repository.PracticeLessonRepository;
import com.example.learnSecurity.repository.PracticeRepository;

/** 実習講義依存関連処理実装クラス */
@Service
@Transactional
public class PracticeLessonServiceImpl implements PracticeLessonService {
	@Autowired
	PracticeRepository practiceRepo;
	
	@Autowired
	LessonRepository lessonRepo;
	
	@Autowired
	PracticeLessonRepository plRepo;
	
	
	@Override
	public List<LessonLinkView> selectRelationLesson(Integer practiceId) {
		List<Integer> LessonIdList = plRepo.findBypracticeId(practiceId);
		List<LessonLinkView>  LessonLinkList = new ArrayList<>();
		lessonRepo.findAllById(LessonIdList).forEach(l -> LessonLinkList.add(new LessonLinkView(l)));
		
		return LessonLinkList;
	}
}
