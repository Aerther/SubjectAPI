package com.example.subject_api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.subject_api.model.Question;
import com.example.subject_api.model.Subject;
import com.example.subject_api.response.ResponseHandler;
import com.example.subject_api.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	
	SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping("/{subjectId}")
	public ResponseEntity<Object> getSubject(@PathVariable("subjectId") String subjectId) {
		Subject subj = this.subjectService.getSubject(subjectId);
		
		return ResponseHandler.responseBuilder("Given requested Subject details", HttpStatus.OK, subj);
	}
	
	@GetMapping("/{subjectId}/questions")
	public ResponseEntity<Object> getSubjectQuestions(@PathVariable("subjectId") String subjectId) {
		List<Question> questions = this.subjectService.getSubjectQuestion(subjectId);
		
		return ResponseHandler.responseBuilder("Given Questions details", HttpStatus.OK, questions);
	}
	
	@PostMapping
	public ResponseEntity<Object> createSubject(@RequestBody Subject subject) {
		this.subjectService.createSubject(subject);
		
		return ResponseHandler.responseBuilder("Requested Subject Saved", HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Object> updateSubject(@RequestBody Subject subject) {
		this.subjectService.updateSubject(subject);
		
		return ResponseHandler.responseBuilder("Requested Subject Updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/{subjectId}")
	public ResponseEntity<Object> deleteSubject(@PathVariable("subjectId") String subjectId) {
		this.subjectService.deleteSubject(subjectId);
		
		return ResponseHandler.responseBuilder("Requested Subject Deleted", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllSubjects() {
		List<Subject> subjects = this.subjectService.getAllSubjects();
		
		return ResponseHandler.responseBuilder("Given Subjects data", HttpStatus.OK, subjects);
	}
}
