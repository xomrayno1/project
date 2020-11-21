package com.tampro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dao.EmployeeRepository;
import com.tampro.dao.ProjectRepository;
import com.tampro.entities.Employee;
import com.tampro.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository employRepo;
	
	
	@GetMapping
	public String displayProject(Model model) {
		List<Project> projects =   proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("formData", new Project());
		List<Employee> employees = employRepo.findAll();
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
//	@PostMapping("/save")		1- n						// danh sách requestParam có tên employees
//	public String saveProject(Project project, Model model,@RequestParam List<Long> employees) {
//		proRepo.save(project);
//		Iterable<Employee>	chosenEmployees	 =	employRepo.findAllById(employees);
//							// Tìm tất cả employee theo danh sách Id  
//		for(Employee employee : chosenEmployees) {
//			employee.setProject(project);
//			employRepo.save(employee);
//		}
//		return "redirect:/projects/new";
//	}
	
	@PostMapping("/save")		//n-n						 
	public String saveProject(Project project, Model model) {
		proRepo.save(project);	 
		return "redirect:/projects";
	}
	@GetMapping("/update")
	public String displayProjectUpdateForm(Model model,@RequestParam("id") long id) {
		Project project = 	proRepo.findById(id).get();
		model.addAttribute("formData", project);
		return "projects/new-project";
	} 
	@GetMapping("/delete")
	public String deleteproject(Model model,@RequestParam("id") long id) {
		proRepo.deleteById(id);
		return "redirect:/projects";
	} 
}
