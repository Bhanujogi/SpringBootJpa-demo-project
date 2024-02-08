package com.bj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bj.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
	
	
}

