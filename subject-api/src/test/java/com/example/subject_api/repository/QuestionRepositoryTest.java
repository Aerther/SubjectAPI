package com.example.subject_api.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

// import this
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.subject_api.model.Question;
import com.example.subject_api.model.Subject;

// mvnw test
// mvnw -Dtest=QuestionRepositoryTest#testFindBySubjectName_NotFound test
// mvnw clena verify        Dont forget to add the plugin and properties

@DataJpaTest
public class QuestionRepositoryTest {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	Question question;
	Subject subject;
	
	@BeforeEach
	void setUp() {
		subject = new Subject("math1", "Math");
		question = new Question("qm1", "What's 1 + 1?", List.of("1", "2", "3", "4"), 1, subject);
		
		this.subjectRepository.save(subject);
		this.questionRepository.save(question);
	}
	
	@AfterEach
	void tearDown() {
		subject = null;
		question = null;
		
		this.questionRepository.deleteAll();
		this.subjectRepository.deleteAll();
	}
	
	@Test
	void testFindBySubjectId_Found() {
		List<Question> questions = this.questionRepository.findBySubjectId("math1");
		
		assertThat(questions.get(0).getSubject().getId(), is(subject.getId()));
		assertThat(questions.get(0).getSubject().getName(), is(subject.getName()));
	}
	
	@Test
	void testFindBySubjectId_NotFound() {
		List<Question> questions = this.questionRepository.findBySubjectId("math2");
		
		assertThat(questions.isEmpty(), is(true));
		
		// Code: assertj
		// assertThat(questions.isEmpty()).isTrue();
	}
}