package com.example.subject_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subject_api.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String> {
	public List<Question> findBySubjectId(String subjectId);
	
	public void deleteBySubjectId(String subjectId);
}