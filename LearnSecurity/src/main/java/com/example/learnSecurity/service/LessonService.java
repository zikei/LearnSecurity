package com.example.learnSecurity.service;

import java.util.List;

import com.example.learnSecurity.entity.Lesson;

/** 講義関連処理 */
public interface LessonService {
	/** 実習を全件取得 */
	List<Lesson> selectAllPractice();
}