package com.example.subject_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subject_api.model.Question;
import com.example.subject_api.service.SubjectService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	SubjectService subjectService;
	
	public QuestionController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping("{questionId}")
	public Question getQuestion(@PathVariable String questionId) {
		return this.subjectService.getQuestion(questionId);
	}
	
	@PostMapping
	public String createQuestion(@RequestBody Question question) {
		this.subjectService.createQuestion(question);
		
		return "Question Saved!";
	}
}
