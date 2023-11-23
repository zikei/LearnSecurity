package com.example.learnSecurity.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Practice;

/**
 * 実習テーブル：リポジトリ
 */
public interface PracticeRepository extends CrudRepository<Practice, Integer> {
	/** 検索ワードで実習を検索 */
	@Query("SELECT * FROM Practice WHERE PracticeName LIKE :word")
	List<Practice> findSearchPracticeName(String word);
}