package com.example.learnSecurity.service;

import java.util.List;

import com.example.learnSecurity.entity.Practice;

/** 実習関連処理 */
public interface PracticeService {
	/** 実習を全件取得 */
	List<Practice> selectAllPractice();
}