package com.example.learnSecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Completion;

/**
 * 修了テーブル：リポジトリ
 */
public interface CompletionRepository extends CrudRepository<Completion, Integer> {
}