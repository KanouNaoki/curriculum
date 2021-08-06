package com.example.security.springsecurity.account;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//問２－１ リポジトリを作成するアノテーションを記述　　親クラス
@Repository // リポジトリの作成 必ずインターフェースを定義しなければならない 保管される場所、リポジトリにつける
public interface AccountRepository extends CrudRepository<Account, Long> {
	public Account findByUsername(String username);

	public List<Account> findAll();
}