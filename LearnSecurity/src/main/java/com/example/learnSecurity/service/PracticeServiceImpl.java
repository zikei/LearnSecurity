package com.example.learnSecurity.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.entity.Instance;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.repository.InstanceRepository;
import com.example.learnSecurity.repository.PracticeRepository;

/** 実習関連処理実装クラス　*/
@Service
@Transactional
public class PracticeServiceImpl  implements PracticeService{
	@Autowired
	PracticeRepository practiceRepo;
	
	@Autowired
	InstanceRepository insRepo;
	
	@Override
	public List<Practice> selectAllPractice() {
		List<Practice> practiceList = new ArrayList<>();
		practiceRepo.findAll().forEach(practiceList::add);
		
		return practiceList;
	}

	@Override
	public List<Instance> selectInstanceByUserId(Integer userId) {
		List<Instance> insList = new ArrayList<>();
		insRepo.findByUserId(userId).forEach(insList::add);
		
		return insList;
	}
}