package com.example.learnSecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Lesson;

/**
 * 講義テーブル：リポジトリ
 */
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}