package com.example.learnSecurity.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.PracticeLesson;

/**
 * 実習講義依存関係テーブル：リポジトリ
 */
public interface PracticeLessonRepository extends CrudRepository<PracticeLesson, Integer> {
	/** 実習IDから関連する講義IDを全て取得する */
	@Query("SELECT LessonId FROM PracticeLesson WHERE PracticeId = :practiceId")
	List<Integer> findBypracticeId(Integer practiceId);
}