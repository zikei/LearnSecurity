package com.example.learnSecurity.data;

import com.example.learnSecurity.entity.Instance;
import com.example.learnSecurity.entity.Practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 実習インスタンスデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeInstance {
	/** 実習インスタンスID */
	private Integer instanceId;
	
	/** 実習ID */
	private Integer practiceId;
	
	/** ユーザID */
	private Integer userId;
	
	/** データベースパス */
	private String dbPath;
	
	/** 実習プロジェクトパス */
	private String projectPath;
	
	public PracticeInstance(Instance ins, Practice practice) {
		this.instanceId  = ins.getInstanceId();
		this.practiceId  = ins.getPracticeId();
		this.userId      = ins.getUserId();
		this.dbPath      = ins.getDbPath();
		this.projectPath = practice.getProjectPath();
	}
}