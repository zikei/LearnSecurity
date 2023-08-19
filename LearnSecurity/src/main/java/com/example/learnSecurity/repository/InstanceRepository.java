package com.example.learnSecurity.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Instance;

/**
 * 実習インスタンステーブル：リポジトリ
 */
public interface InstanceRepository extends CrudRepository<Instance, Integer> {
	/** ユーザIDで検索を行う */
	@Query("SELECT * FROM Instance WHERE UserId = :userId")
	Iterable<Instance> findByUserId(Integer userId);
}