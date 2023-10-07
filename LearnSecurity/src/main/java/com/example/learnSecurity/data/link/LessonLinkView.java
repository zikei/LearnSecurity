package com.example.learnSecurity.data.link;

import com.example.learnSecurity.entity.Lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 講義リンク表示用クラス */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonLinkView {
	/** 講義ID */
	private Integer lessonId;
	
	/** 講義名 */
	private String lessonName;
	
	public LessonLinkView(Lesson lesson) {
		this.lessonId   = lesson.getLessonId();
		this.lessonName = lesson.getLessonName();
	}
}
