package com.example.subject_api.service;

import java.util.List;

import com.example.subject_api.model.Question;
import com.example.subject_api.model.Subject;

public interface SubjectService {
	public String createSubject(Subject subject);
	public String createQuestion(Question question);
	
	public String updateSubject(Subject subject);
	public String updateQuestion(Question question);
	
	public String deleteSubject(String id);
	public String deleteQuestion(String id);
	
	public Subject getSubject(String id);
	public Question getQuestion(String id);
	
	public List<Question> getSubjectQuestion(String subjectId);
	public List<Subject> getAllSubjects();
}
