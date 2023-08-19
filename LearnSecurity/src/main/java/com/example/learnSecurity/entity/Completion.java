package com.example.learnSecurity.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修了テーブル：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Completion {
	/** 講義ID:PK */
	@Id
	@Column(value="completionid")
	private Integer completionID;
	
	/** 講義ID:FK */
	@Column(value="lessonid")
	private Integer lessonId;
	
	/** ユーザID:FK */
	@Column(value="userid")
	private Integer userId;
	
	/** 修了日 */
	@Column(value="completiondate")
	private Date completionDate;
}