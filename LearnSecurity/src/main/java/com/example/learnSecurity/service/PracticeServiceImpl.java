package com.example.learnSecurity.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.PracticeView;
import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;
import com.example.learnSecurity.repository.PracticeRepository;

/** 実習関連処理実装クラス　*/
@Service
@Transactional
public class PracticeServiceImpl  implements PracticeService{
	@Autowired
	PracticeRepository practiceRepo;
	
	@Autowired
	PracticeLessonService plService;
	
	@Override
	public List<Practice> selectAllPractice() {
		List<Practice> practiceList = new ArrayList<>();
		practiceRepo.findAll().forEach(practiceList::add);
		
		return practiceList;
	}
	
	@Override
	public PracticeView selectPracticeView(Integer practiceId) throws NotFoundException{
		Practice practice = selectPractice(practiceId);
		List<LessonLinkView> relationLessonList = plService.selectRelationLesson(practiceId);
		
		return new PracticeView(practice, relationLessonList);
	}

	@Override
	public String selectPracticeDirPath(Integer practiceId) throws NotFoundException{
		Practice practice = selectPractice(practiceId);
		return System.getenv("PRACTICE_DIR") + practice.getDirPath();
	}
	
	
	private Practice selectPractice(Integer practiceId) throws NotFoundException {
		return practiceRepo.findById(practiceId)
				.orElseThrow(() -> new NotFoundException("404 PracticeID : " + practiceId));
	}
}