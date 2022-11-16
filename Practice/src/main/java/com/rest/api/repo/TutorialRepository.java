package com.rest.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.entity.Tutorial;

@Repository

public interface TutorialRepository extends JpaRepository<Tutorial,Long>{
	
	public List<Tutorial> findByPublished(boolean published);
	
	public List<Tutorial> findByTitleContaining(String title);

}
