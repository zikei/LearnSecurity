package com.example.learnSecurity.data;

import java.util.List;

import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.entity.Practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 実習表示用データ */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeView {
	/** 実習ID */
	private Integer practiceId;
	
	/** 実習名 */
	private String practiceName;
	
	/** 実習プロジェクト説明 */
	private String practiceInfo;
	
	/** 関連講義リンクリスト */
	private List<LessonLinkView> relationLessonList;
	
	public PracticeView(Practice practice, List<LessonLinkView> relationLessonList) {
		this.practiceId   = practice.getPracticeId();
		this.practiceName = practice.getPracticeName();
		this.practiceInfo = practice.getPracticeInfo();
		this.relationLessonList = relationLessonList;
	}
}
