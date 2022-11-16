package com.rest.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.entity.Tutorial;
import com.rest.api.repo.TutorialRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*")
public class Tutorialcontroller {
	
	@Autowired TutorialRepository TutRep;
	
	@GetMapping("/tutorials/{id}")	
	public ResponseEntity<Tutorial> findById(@PathVariable("id") long id){
		
		Optional<Tutorial> t=TutRep.findById(id);
		
		if(t.isPresent())
		return new ResponseEntity<>(t.get(),HttpStatus.OK);
		else
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished(){
		try {
		List<Tutorial> t=TutRep.findByPublished(true);		
		if(t.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(t,HttpStatus.OK);
		}
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tutorials/title")
	public ResponseEntity<List<Tutorial>>findByTitleContaining(@RequestParam("title") String title) {
		
		List<Tutorial> tList=TutRep.findByTitleContaining(title);
		if(tList.isEmpty()) {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(tList,HttpStatus.OK);
		}
	}
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> findAll(){
		try {
		List<Tutorial> tList=TutRep.findAll();
		if(tList.isEmpty()) {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(tList,HttpStatus.OK);
		}
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial t){
		try {
		Tutorial tut=TutRep.save(new Tutorial(t.getTitle(),t.getDescription(),t.isPublished()));
		return new ResponseEntity<>(tut,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}		
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable long id,@RequestBody Tutorial t) {
		Optional<Tutorial> tutData=TutRep.findById(id);
		if(tutData.isPresent()) {
			Tutorial  tut=tutData.get();
			tut.setTitle(t.getTitle());
			tut.setDescription(t.getDescription());
			tut.setPublished(t.isPublished());			
			return new ResponseEntity<>(TutRep.save(tut),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
	}
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable long id){
		TutRep.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/tutorials")	
	public ResponseEntity<HttpStatus> deleteAll(){
		TutRep.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
