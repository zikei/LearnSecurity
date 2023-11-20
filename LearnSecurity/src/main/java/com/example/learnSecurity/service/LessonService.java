package com.example.learnSecurity.service;

import java.util.List;

import com.example.learnSecurity.data.LessonView;
import com.example.learnSecurity.entity.Lesson;
import com.example.learnSecurity.exception.NotFoundException;

/** 講義関連処理 */
public interface LessonService {
	/** 実習を全件取得 */
	List<Lesson> selectAllLesson();
	
	/** 指定した講義表示情報を取得 */
	LessonView selectLessonView(Integer lessonId) throws NotFoundException;
}