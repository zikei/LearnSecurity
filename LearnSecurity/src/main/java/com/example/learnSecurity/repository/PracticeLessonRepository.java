package com.example.learnSecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.PracticeLesson;

/**
 * 実習講義依存関係テーブル：リポジトリ
 */
public interface PracticeLessonRepository extends CrudRepository<PracticeLesson, Integer> {
}