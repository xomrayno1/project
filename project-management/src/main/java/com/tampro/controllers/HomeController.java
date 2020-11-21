package com.tampro.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tampro.dao.EmployeeRepository;
import com.tampro.dao.ProjectRepository;
import com.tampro.dto.ChartData;
import com.tampro.dto.EmployeeProject;
import com.tampro.entities.Project;

@Controller
public class HomeController {

	@Value("${version}")
	private String version;
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository employRepo;	
	
	@GetMapping(value = {"/","home"})
	public String displayHome(Model model) throws JsonProcessingException {
		model.addAttribute("version", version);
		
		List<Project> projects =   proRepo.findAll();
		model.addAttribute("projects", projects);
		
		List<ChartData> projectData = 	proRepo.getProjectStatus();
		Map<String, Object> map = new HashMap<>();
		
		ObjectMapper objectMapper  = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		System.out.println(jsonString);
		model.addAttribute("projectStatusCnt", jsonString);
		
		List<EmployeeProject> employeeProjectCnt = employRepo.employeeProjects();
		model.addAttribute("employeeListProjectCnt", employeeProjectCnt);
		return "main/home";
	}

}
