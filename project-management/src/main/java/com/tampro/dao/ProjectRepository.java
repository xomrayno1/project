package com.tampro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tampro.dto.ChartData;
import com.tampro.entities.Project;
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	@Override
	public	List<Project> findAll();
	
	@Query(nativeQuery = true, value = "SELECT stage as label , count(*) as value FROM PROJECT  " + 
			"group by stage  ")
	public 	List<ChartData>	getProjectStatus();
}
