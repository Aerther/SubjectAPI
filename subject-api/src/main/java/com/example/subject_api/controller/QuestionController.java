package com.example.subject_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subject_api.model.Question;
import com.example.subject_api.response.ResponseHandler;
import com.example.subject_api.service.SubjectService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	SubjectService subjectService;
	
	public QuestionController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Object> getQuestion(@PathVariable("questionId") String questionId) {
		Question question = this.subjectService.getQuestion(questionId);
		
		return ResponseHandler.responseBuilder("Given Question details", HttpStatus.OK, question);
	}
	
	@PostMapping
	public ResponseEntity<Object> createQuestion(@RequestBody Question question) {
		this.subjectService.createQuestion(question);
		
		return ResponseHandler.responseBuilder("Requested Question Saved", HttpStatus.CREATED);
	}
}
