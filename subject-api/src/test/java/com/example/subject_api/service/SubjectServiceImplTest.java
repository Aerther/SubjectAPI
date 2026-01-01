package com.example.subject_api.service;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.subject_api.model.Question;
import com.example.subject_api.model.Subject;
import com.example.subject_api.repository.QuestionRepository;
import com.example.subject_api.repository.SubjectRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

public class SubjectServiceImplTest {
	
	@Mock
	private QuestionRepository questionRepository;
	
	@Mock
	private SubjectRepository subjectRepository;
	
	private SubjectService subjectService;
	
	AutoCloseable autoCloseable;
	
	Question question;
	Subject subject;
	
	@BeforeEach
	void setUp() {
		this.autoCloseable = MockitoAnnotations.openMocks(this);
		
		this.subjectService = new SubjectServiceImpl(this.subjectRepository, this.questionRepository);
		
		this.subject = new Subject("math1", "Math");
		this.question = new Question("qm1", "What's 1 + 1?", List.of("1", "2", "3", "4"), 1, subject);
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
		this.autoCloseable.close();
	}
	
	@Test
	void testCreateSubject() {
		mock(Subject.class);
		
		mock(SubjectRepository.class);
		
		when(this.subjectRepository.save(this.subject)).thenReturn(this.subject);
		
		assertThat(this.subjectService.createSubject(subject), is("sucess"));
	}
	
	// All methods used on the service have to be wrapped on a when method
	
	@Test
	void testCreateQuestion() {
		mock(Subject.class);
		mock(Question.class);
		
		mock(SubjectRepository.class);
		mock(QuestionRepository.class);
		
		when(this.subjectRepository.save(this.subject)).thenReturn(this.subject);
		when(this.subjectRepository.findById(this.subject.getId())).thenReturn(Optional.of(this.subject));
		
		when(this.questionRepository.save(this.question)).thenReturn(this.question);
		
		assertThat(this.subjectService.createSubject(subject), is("sucess"));
		assertThat(this.subjectService.createQuestion(question), is("sucess"));
	}
	
	@Test
	void testGetSubjectQuestion() {
		mock(Subject.class);
		mock(Question.class);
		
		mock(SubjectRepository.class);
		mock(QuestionRepository.class);
		
		when(this.subjectRepository.findById(this.subject.getId())).thenReturn(Optional.of(this.subject));
		when(this.questionRepository.findBySubjectId(this.subject.getId())).thenReturn(List.of(this.question));
		
		assertThat(this.subjectService.getSubjectQuestion(this.subject.getId()), is(List.of(this.question)));
	}
	
	@Test
	void testGetAllSubjects() {
		mock(Subject.class);
		
		mock(SubjectRepository.class);
		
		when(this.subjectRepository.findAll()).thenReturn(List.of(this.subject));
		
		assertThat(this.subjectService.getAllSubjects().get(0).getId(), is(this.subject.getId()));
	}
	
	// Delete is done like this
	
	@Test
	void testDeleteQuestion() {
		mock(Question.class);
		mock(QuestionRepository.class, Mockito.CALLS_REAL_METHODS);
		
		doAnswer(Answers.CALLS_REAL_METHODS).when(this.questionRepository).deleteById(any());
		
		assertThat(this.subjectService.deleteQuestion(this.question.getId()), is("sucess"));
	}
}
