package com.example.learnSecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.data.link.PracticeLinkView;

/** 実習講義依存関連処理 */
@Service
@Transactional
public interface PracticeLessonService {
	/** 実習IDから関連する講義のリンククラスを取得 */
	List<LessonLinkView> selectRelationLesson(Integer practiceId);
	
	/** 講義IDから関連する実習のリンククラスを取得 */
	List<PracticeLinkView> selectRelationPractice(Integer lessonId);
}
