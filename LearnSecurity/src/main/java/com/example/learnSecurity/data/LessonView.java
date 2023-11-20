package com.example.learnSecurity.data;

import java.util.List;

import com.example.learnSecurity.data.link.PracticeLinkView;
import com.example.learnSecurity.entity.Lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 講義表示用データ */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonView {
	/** 講義ID */
	private Integer lessonId;
	
	/** 講義名 */
	private String lessonName;
	
	/** 講義本編 */
	private String content;
	
	/** 関連実習リンクリスト */
	private List<PracticeLinkView> relationPracticeList;

	public LessonView(Lesson lesson, String content,
			List<PracticeLinkView> relationPracticeList) {
		this.lessonId   = lesson.getLessonId();
		this.lessonName = lesson.getLessonName();
		this.content    = content;
		this.relationPracticeList = relationPracticeList;
	}
}
