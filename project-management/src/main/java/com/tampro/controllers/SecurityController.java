package com.tampro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tampro.dao.UserAccountRepository;
import com.tampro.entities.UserAccount;

@Controller
public class SecurityController {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@GetMapping(value = {"/register"})
	public String register(Model model)  {
		model.addAttribute("userAccount", new UserAccount());
		return "security/register";
	}
	
	@PostMapping(value = {"/register/save"})
	public String saveUser(Model model,@ModelAttribute("userAccount") UserAccount userAccount)  {
		userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
		userAccountRepository.save(userAccount);
		return "redirect:/register";
	}
}
