package com.tampro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tampro.dto.EmployeeProject;
import com.tampro.entities.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true,value = "select e.first_name as lastname,e.last_name  as firstName, "
			+ "count(pe.employee_id) as projectCount " + 
			"from EMPLOYEE as  e left join PROJECT_EMPLOYEE as pe " + 
			"ON e.employee_id = pe.employee_id " + 
			"group by e.first_name,e.last_name order by 3 desc")
	public List<EmployeeProject> employeeProjects();
	
	
	public Employee findByEmail(String email);

	public Employee findByEmployeeId(long id);
}
