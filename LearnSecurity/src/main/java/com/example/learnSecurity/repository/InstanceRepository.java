package com.example.learnSecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learnSecurity.entity.Instance;

/**
 * 実習インスタンステーブル：リポジトリ
 */
public interface InstanceRepository extends CrudRepository<Instance, Integer> {
}