package com.example.learnSecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Practice;

/**
 * 実習テーブル：リポジトリ
 */
public interface PracticeRepository extends CrudRepository<Practice, Integer> {
}