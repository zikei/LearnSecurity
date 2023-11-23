package com.example.learnSecurity.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Lesson;

/**
 * 講義テーブル：リポジトリ
 */
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
	/** 検索ワードで講義を検索 */
	@Query("SELECT * FROM Lesson WHERE LessonName LIKE :word")
	List<Lesson> findSearchLessonName(String word);
}