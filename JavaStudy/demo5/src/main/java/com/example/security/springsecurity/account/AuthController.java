package com.example.security.springsecurity.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//問４－１ コントローラーを意味するアノテーションを記述
//画面遷移用のコントローラーに付与する
@Controller // 画面遷移用のコントローラーに付与する
public class AuthController {

	@Autowired
	private AccountRepository repository;

	public List<Account> get() {
		return (List<Account>) repository.findAll();
	}

	@RequestMapping("/") // いろんな処理の場所に行く
	public String index() {
		return "redirect:/top";
	}

	@GetMapping("/login") // 登録されているデータを取ってくる
	public String login() {
		return "login";
	}

	@PostMapping("/login") // 新しいデータを登録する 投稿の役割
	public String loginPost() {
		return "redirect:/login-error";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping("/top")
	public String top() {
		return "/top";
	}

}