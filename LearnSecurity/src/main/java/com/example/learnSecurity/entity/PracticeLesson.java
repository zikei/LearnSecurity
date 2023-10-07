package com.example.learnSecurity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 実習講義依存関係テーブル：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeLesson {
	/** 依存ID:PK */
	@Id
	@Column(value="practicelessonid")
	private Integer plId;
	
	/** 講義ID:FK */
	@Column(value="lessonid")
	private Integer lessonId;
	
	/** 実習ID:FK */
	@Column(value="practiceid")
	private Integer practiceId;
}