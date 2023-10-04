package com.example.learnSecurity.data.link;

import com.example.learnSecurity.entity.Practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 実習リンク表示用クラス */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeLinkView {
	/** 実習ID */
	private Integer practiceId;
	
	/** 実習名 */
	private String practiceName;
	
	public PracticeLinkView(Practice practice) {
		this.practiceId   = practice.getPracticeId();
		this.practiceName = practice.getPracticeName();
	}
}
