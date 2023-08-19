package com.example.learnSecurity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 講義テーブル：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
	/** 講義ID:PK */
	@Id
	@Column(value="lessonid")
	private Integer lessonId;
	
	/** 講義名:unique */
	@Column(value="lessonname")
	private String lessonName;
	
	/** 講義パス */
	@Column(value="lessonpath")
	private String lessonPath;
}