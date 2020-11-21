package com.tampro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dao.EmployeeRepository;
import com.tampro.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository employRepo;
	
	@GetMapping
	public String displayEmloyee(Model model) {
		List<Employee> employees = employRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	@GetMapping("/new")
	public String displayEmloyeeForm(Model model) {
		model.addAttribute("formData", new Employee());
		return "employees/new-employee";
	}
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long id,Model model) {
		Employee employee =	employRepo.findByEmployeeId(id);
		model.addAttribute("formData", employee);
		return "employees/new-employee";
	}
	@PostMapping("/save")
	public String saveEmployee(Model model, @ModelAttribute("formData") @Validated Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			return "employees/new-employee"; 
		}
		employRepo.save(employee);
		return "redirect:/employees/new";
	}
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id,Model model) {
		employRepo.deleteById(id);
		return "redirect:/employees";
	}
}
