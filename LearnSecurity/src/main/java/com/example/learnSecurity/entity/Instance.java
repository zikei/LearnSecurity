package com.example.learnSecurity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 実習インスタンステーブル：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instance {
	/** 実習インスタンスID:PK */
	@Id
	@Column(value="instanceid")
	private Integer instanceId;
	
	/** 実習ID:FK */
	@Column(value="practiceid")
	private Integer practiceId;
	
	/** ユーザID:FK */
	@Column(value="userid")
	private Integer userId;
	
	/** 実習インスタンス名 */
	@Column(value="instancename")
	private String instanceName;
	
	/** データベースパス */
	@Column(value="dbpath")
	private String dbPath;
}