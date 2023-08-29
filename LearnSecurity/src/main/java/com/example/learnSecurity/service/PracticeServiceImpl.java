package com.example.learnSecurity.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.PracticeInstance;
import com.example.learnSecurity.entity.Instance;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;
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

	@Override
	public PracticeInstance selectPracticeInstance(Integer insId) throws NotFoundException {
		Instance ins      = insRepo.findById(insId).orElseThrow(() -> new NotFoundException("404 InstanceID : " + insId));
		Practice practice = practiceRepo.findById(ins.getPracticeId()).orElseThrow(() -> new NotFoundException("404 PracticeID : " + insId));
		return new PracticeInstance(ins, practice);
	}
}