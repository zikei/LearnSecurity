package com.example.learnSecurity.service;

import java.util.List;

import com.example.learnSecurity.data.PracticeInstance;
import com.example.learnSecurity.entity.Instance;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;

/** 実習関連処理 */
public interface PracticeService {
	/** 実習を全件取得 */
	List<Practice> selectAllPractice();
	
	/** 指定したユーザの実習インスタンスを取得 */
	List<Instance> selectInstanceByUserId(Integer userId);
	
	/** 指定したインスタンスのデータを取得 
	 * @throws NotFoundException */
	PracticeInstance selectPracticeInstance(Integer insId) throws NotFoundException;
}