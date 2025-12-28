package com.example.subject_api.controller;

import java.util.List;

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
import com.example.subject_api.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	
	SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping("/{subjectId}")
	public List<Question> getSubjectQuestions(@PathVariable("subjectId") String subjectId) {
		return this.subjectService.getSubjectQuestion(subjectId);
	}
	
	@PostMapping
	public String createSubject(@RequestBody Subject subject) {
		this.subjectService.createSubject(subject);
		
		return "Subject Saved!";
	}
	
	@PutMapping
	public String updateSubject(@RequestBody Subject subject) {
		this.subjectService.updateSubject(subject);
		
		return "Subject Updated!";
	}
	
	@DeleteMapping("/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") String subjectId) {
		this.subjectService.deleteSubject(subjectId);
		
		return "Subject Deleted!";
	}
	
	@GetMapping("/all")
	public List<Subject> getAllSubjects() {
		return this.subjectService.getAllSubjects();
	}
}
