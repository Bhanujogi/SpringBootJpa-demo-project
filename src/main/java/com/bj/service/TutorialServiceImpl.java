package com.bj.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bj.model.Tutorial;
import com.bj.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;

	@Override
	public ResponseEntity<Tutorial> createTutorial(Tutorial tutorial) {

		System.out.println("Tutorial Service :" + tutorial.getTitle() + " " + tutorial.getDescription() + " "
				+ tutorial.getId() + " " + tutorial.isPublished());
		
		try {
			Tutorial _tutorial = tutorialRepository.save(tutorial);
			return new ResponseEntity<Tutorial>(tutorial, HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	

	@Override
	public Optional<Tutorial> getTutorialById(long id) {

		Optional<Tutorial> tutorial = tutorialRepository.findById(id);

		return tutorial;
	}

	@Override
	public List<Tutorial> getAllTutorials() {

		List<Tutorial> empList = tutorialRepository.findAll();

		return empList;

	}

	@Override
	public ResponseEntity<Tutorial> updateTutorials(long id, Tutorial tutorial) {

		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {

			Tutorial _tutorial = tutorialData.get();

			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());

			return new ResponseEntity<Tutorial>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteTutorial(long id) {
		// TODO Auto-generated method stub

		System.out.println("Service impl " + id);

		tutorialRepository.deleteById(id);
	}

	@Override
	public void deleteAllTutorials() {
		// TODO Auto-generated method stub
		
		System.out.println("Service layer!!!");
        tutorialRepository.deleteAll();

	}

}
