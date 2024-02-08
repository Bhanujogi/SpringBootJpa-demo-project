package com.bj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bj.model.Tutorial;
import com.bj.service.TutorialService;

@RestController
@RequestMapping("/api")
public class TutorialController {

	// TutorialServiceImpl tutorialServiceImpl = new TutorialServiceImpl();

	@Autowired
	private TutorialService tutorialService;

	// http://localhost:8080/api/
	// API TO POST RECORD (to insert record in db)
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {

		System.out.println("Tutorial Contoller: " + tutorial.getId() + " " + tutorial.getTitle() + " "
				+ tutorial.getDescription() + " " + tutorial.isPublished());

		try {
			ResponseEntity<Tutorial> responseEntity = tutorialService.createTutorial(tutorial);
			return responseEntity;
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	// API TO RETRIVE SIGLE RECORD (to fatch record from db)
	// http://localhost:8080/tutorial/102
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {

		
		Optional<Tutorial> tutorial = tutorialService.getTutorialById(id);

		if (tutorial.isPresent()) {

			return new ResponseEntity<Tutorial>(tutorial.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<Tutorial>(HttpStatus.NOT_FOUND);

		}
	}

	// API TO RETRIEVE ALL RECORED
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTurotials() {

		try {

			List<Tutorial> tutorialList = tutorialService.getAllTutorials();

			return new ResponseEntity<List<Tutorial>>((List<Tutorial>) tutorialList, HttpStatus.OK);

			} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		 }
	}
	
	
	//API TO UPDATE RECORD (get/modify/save)
	//http://localhost:8080/api/102
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorials(@PathVariable("id") long id, @RequestBody Tutorial tutorial){
		
		System.out.println(id+" "+tutorial.getTitle());
		
		 ResponseEntity<Tutorial> tutorialData = tutorialService.updateTutorials(id, tutorial);

	        return tutorialData;

	    }

	//http://localhost:8099/api/tutorials/5
	    @DeleteMapping("/tutorials/{id}")
	    public ResponseEntity<Tutorial> deleteTutorial(@PathVariable("id") long id) {

	        System.out.println("Controller " + id);
	        try {
	            tutorialService.deleteTutorial(id);
	            return new ResponseEntity<Tutorial>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<Tutorial>(HttpStatus.INTERNAL_SERVER_ERROR);

	        }

	    }
	    
	    @DeleteMapping("/tutorials")
	    public ResponseEntity<Tutorial> deleteAllTutorials(){
	        
	        System.out.println("Controller layer!!!");
	        
	        tutorialService.deleteAllTutorials();
	        
	        return null;
	    }

	
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

