package com.example.security.springsecurity.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//実行時に宣言した各フィールド変数がカラムとして作成される
//問１－１ DB設計に必要なアノテーションを記述　　コンパイラで出力される警告メッセージを抑制したりプログラムの動作変更　PCが分かるようにアノテーションを入れている
//Entityクラス(データの入れ物となるクラス) データベースに接続出来ない
@Entity
@Table(name = "accounts") // テーブル名
public class Account implements UserDetails {

	private static final long serialVersionUID = 1L;

	// 権限は一般ユーザ、マネージャ、システム管理者の３種類とする
	public enum Authority {
		ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
	}

	// 問１－２ プライマリーキーを設定するアノテーションを記述
	@Id // 主キーを指定
	@Column(nullable = false, unique = true) // プロパティがユニークキーであるかどうかを指定する他の行の値と重複しちゃダメよ制約
	private String username;

	@Column(nullable = false) // カラムにnull値を指定できるかどうかを指定する
	private String password;

	@Column(nullable = false, unique = true)
	private String mailAddress;

	@Column(nullable = false)
	private boolean mailAddressVerified;

	@Column(nullable = false)
	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP) // 日付と時刻
	private Date createdAt;

	// roleは複数管理できるように、Set<>で定義。
	@ElementCollection(fetch = FetchType.EAGER) // 基本型のコレクション型を即時ロード
	@Enumerated(EnumType.STRING) // Enum の値を DB に格納することを宣言する
	@Column(nullable = false) // null を許容しないことを明示
	private Set<Authority> authorities;

	// JPA requirement
	protected Account() {
	}

	// コンストラクタ インスタンス火とほぼ同時
	public Account(String username, String password, String mailAddress) {
		this.username = username;
		this.password = password;
		this.mailAddress = mailAddress;
		this.mailAddressVerified = false;
		this.enabled = true;
		this.authorities = EnumSet.of(Authority.ROLE_USER);
	}

	// 登録時に、日時を自動セットする
	@PrePersist // コールバックメソッド受け身な関数
	public void prePersist() {
		this.createdAt = new Date();
	}

	// admin権限チェック
	public boolean isAdmin() {
		return this.authorities.contains(Authority.ROLE_ADMIN);
	}

	// admin権限セット
	public void setAdmin(boolean isAdmin) {
		if (isAdmin) {
			this.authorities.add(Authority.ROLE_MANAGER);
			this.authorities.add(Authority.ROLE_ADMIN);
		} else {
			this.authorities.remove(Authority.ROLE_ADMIN);
		}
	}

	// 管理者権限を保有しているか？
	public boolean isManager() {
		return this.authorities.contains(Authority.ROLE_MANAGER);
	}

	// 管理者権限セット
	public void setManager(boolean isManager) {
		if (isManager) {
			this.authorities.add(Authority.ROLE_MANAGER);
		} else {
			this.authorities.remove(Authority.ROLE_MANAGER);
			this.authorities.remove(Authority.ROLE_ADMIN);
		}
	}

	@Override // 親クラスのメソッドをオーバーライドしている
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Authority authority : this.authorities) {
			authorities.add(new SimpleGrantedAuthority(authority.toString()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public boolean isMailAddressVerified() {
		return mailAddressVerified;
	}

	public void setMailAddressVerified(boolean mailAddressVerified) {
		this.mailAddressVerified = mailAddressVerified;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}