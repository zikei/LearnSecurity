package com.example.learnSecurity.service;

import java.util.List;

import com.example.learnSecurity.data.PracticeView;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;

/** 実習関連処理 */
public interface PracticeService {
	/** 実習を全件取得 */
	List<Practice> selectAllPractice();
	
	/** 指定した実習表示情報を取得 */
	PracticeView selectPracticeView(Integer practiceId) throws NotFoundException;
	
	/** 指定した実習のディレクトリの絶対パスを取得 */
	String selectPracticeDirPath(Integer practiceId) throws NotFoundException;
}