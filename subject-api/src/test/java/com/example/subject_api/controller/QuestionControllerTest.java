package com.example.subject_api.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.subject_api.model.Question;
import com.example.subject_api.model.Subject;
import com.example.subject_api.service.SubjectService;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private SubjectService subjectService;
	
	Subject subject;
	Question question1;
	Question question2;
	
	List<Question> questions = new ArrayList<>();
	
	
	@BeforeEach
	void setUp() {
		this.subject = new Subject("math1", "Math");
		
		this.question1 = new Question("qm1", "What's 1 + 1?", List.of("1", "2", "3", "4"), 1, subject);
		this.question2 = new Question("qm2", "sin(30°) = x", List.of("1/2", "sqrt(3)/2", "sqrt(2)/2"), 0, subject);
		
		this.questions.add(question1);
		this.questions.add(question2);
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@Test
	void testGetQuestion() throws Exception {
		when(this.subjectService.getQuestion("qm1")).thenReturn(question1);
		
		this.mockMvc.perform(get("/questions/qm1")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testCreateQuestion() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(this.question1);
		
		when(this.subjectService.createQuestion(question1)).thenReturn("sucess");
		
		this.mockMvc.perform(post("/questions").contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andDo(print()).andExpect(status().isCreated());
	}
	
	
}
