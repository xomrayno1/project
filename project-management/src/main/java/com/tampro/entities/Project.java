package com.tampro.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	private String name;
	 
	private String stage; // NOTSTARTED COMPLETED INPROGRESS
	
	private String description;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee"
			,joinColumns =@JoinColumn(name="project_Id")
			,inverseJoinColumns = @JoinColumn(name="employee_Id"))
	@JsonIgnore
	private List<Employee> employees;
	
	
	public Project() {
	 
	}

	public Project(  String name, String stage, String description) {
		 
		 
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public void addEmployee(Employee employee) {
		if(employees == null) {
			employees = new ArrayList<Employee>();
		}
		employees.add(employee);
	}
	
	
}
