package com.example.learnSecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.link.LessonLinkView;

/** 実習講義依存関連処理 */
@Service
@Transactional
public interface PracticeLessonService {
	/** 実習IDから関連する講義のリンククラスを取得 */
	List<LessonLinkView> selectRelationLesson(Integer practiceId);
}
