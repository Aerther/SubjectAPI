package com.example.subject_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.subject_api.exception.NotFoundException;
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
		
		return "sucess";
	}

	@Override
	public String createQuestion(Question question) {
		if(this.subjectRepository.findById(question.getSubject().getId()).isEmpty()) {
			throw new NotFoundException("Request Subject ID does not exist");
		}
		
		this.questionRepository.save(question);
		
		return "sucess";
	}

	@Override
	public String updateSubject(Subject subject) {
		if(this.subjectRepository.findById(subject.getId()).isEmpty()) {
			throw new NotFoundException("Request Subject does not exist");
		}
		
		this.subjectRepository.save(subject);
		
		return "sucess";
	}

	@Override
	public String updateQuestion(Question question) {
		if(this.questionRepository.findById(question.getId()).isEmpty()) {
			throw new NotFoundException("Request Question does not exist");
		}
		
		this.questionRepository.save(question);
		
		return "sucess";
	}

	@Override
	public String deleteSubject(String id) {
		this.questionRepository.deleteBySubjectId(id);
	
		this.subjectRepository.deleteById(id);
		
		return "sucess";
	}

	@Override
	public String deleteQuestion(String id) {
		this.questionRepository.deleteById(id);
		
		return "sucess";
	}

	@Override
	public Subject getSubject(String id) {
		if(this.subjectRepository.findById(id).isEmpty()) {
			throw new NotFoundException("Request Subject does not exist");
		}
		
		return this.subjectRepository.findById(id).get();
	}

	@Override
	public Question getQuestion(String id) {
		if(this.questionRepository.findById(id).isEmpty()) {
			throw new NotFoundException("Request Question does not exist");
		}
		
		return this.questionRepository.findById(id).get();
	}

	@Override
	public List<Question> getSubjectQuestion(String subjectId) {
		if(this.subjectRepository.findById(subjectId).isEmpty()) {
			throw new NotFoundException("Request Subject does not exist");
		}
		
		return this.questionRepository.findBySubjectId(subjectId);
	}

	@Override
	public List<Subject> getAllSubjects() {
		return this.subjectRepository.findAll();
	}
}
