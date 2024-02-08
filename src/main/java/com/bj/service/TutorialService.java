package com.bj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.bj.model.Tutorial;

public interface TutorialService {

	public ResponseEntity<Tutorial> createTutorial(Tutorial tutorial);

	public Optional<Tutorial> getTutorialById(long id);

	public List<Tutorial> getAllTutorials();

	public ResponseEntity<Tutorial> updateTutorials(long id, Tutorial tutorial);

	public void deleteTutorial(long id);

	public void deleteAllTutorials();

}
