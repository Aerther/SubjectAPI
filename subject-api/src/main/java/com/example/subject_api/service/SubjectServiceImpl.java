package com.example.subject_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.subject_api.model.Question;
import com.example.subject_api.model.Subject;
import com.example.subject_api.repository.QuestionRepository;
import com.example.subject_api.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	SubjectRepository subjectRepository;
	QuestionRepository questionRepository;
	
	public SubjectServiceImpl(SubjectRepository subjRepo, QuestionRepository questRepo) {
		this.subjectRepository = subjRepo;
		this.questionRepository = questRepo;
	}

	@Override
	public String createSubject(Subject subject) {
		this.subjectRepository.save(subject);
		
		return "Subject Saved!";
	}

	@Override
	public String createQuestion(Question question) {
		this.questionRepository.save(question);
		
		return "Question Saved!";
	}

	@Override
	public String updateSubject(Subject subject) {
		this.subjectRepository.save(subject);
		
		return "Subject Updated!";
	}

	@Override
	public String updateQuestion(Question question) {
		this.questionRepository.save(question);
		
		return "Question Updated!";
	}

	@Override
	public String deleteSubject(String id) {
		this.questionRepository.deleteBySubjectId(id);
	
		this.subjectRepository.deleteById(id);
		
		return "Subject Deleted!";
	}

	@Override
	public String deleteQuestion(String id) {
		this.questionRepository.deleteById(id);
		
		return "Question Deleted!";
	}

	@Override
	public Subject getSubject(String id) {
		return this.subjectRepository.findById(id).get();
	}

	@Override
	public Question getQuestion(String id) {
		return this.questionRepository.findById(id).get();
	}

	@Override
	public List<Question> getSubjectQuestion(String subjectId) {
		return this.questionRepository.findBySubjectId(subjectId);
	}

	@Override
	public List<Subject> getAllSubjects() {
		return this.subjectRepository.findAll();
	}

}
