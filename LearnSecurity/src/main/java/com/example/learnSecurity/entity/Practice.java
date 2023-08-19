package com.example.learnSecurity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 実習テーブル：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Practice {
	/** 実習ID:PK */
	@Id
	@Column(value="practiceid")
	private Integer practiceId;
	
	/** 実習名:unique */
	@Column(value="practicename")
	private String practiceName;
	
	/** 実習プロジェクトパス */
	@Column(value="projectpath")
	private String projectPath;
	
	/** 実習プロジェクトDB作成SQLファイルパス */
	@Column(value="dbcreatepath")
	private String DbCreatePath;
}