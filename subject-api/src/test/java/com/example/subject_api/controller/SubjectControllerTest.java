package com.example.subject_api.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import tools.jackson.databind.SerializationFeature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private SubjectService subjectService;
	
	private Subject subject1;
	private Subject subject2;
	
	private Question question;
	
	private List<Subject> subjects = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		this.subject1 = new Subject("math", "Math");
		this.subject2 = new Subject("chem", "Chemistry");
		
		this.question = new Question("qm1", "What's 1 + 1?", List.of("1", "2", "3", "4"), 1, subject1);
		
		subjects.add(subject1);
		subjects.add(subject2);
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@Test
	void testGetSubject() throws Exception {
		when(this.subjectService.getSubject(this.subject1.getId())).thenReturn(subject1);
		
		this.mockMvc.perform(get("/subjects/math")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testGetAllSubjects() throws Exception {
		when(this.subjectService.getAllSubjects()).thenReturn(subjects);
		
		this.mockMvc.perform(get("/subjects")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testGetSubjectQuestions() throws Exception {
		when(this.subjectService.getSubjectQuestion(this.subject1.getId())).thenReturn(List.of(question));
		
		this.mockMvc.perform(get("/subjects/math/questions")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testCreateSubject() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(this.subject1);
		
		when(this.subjectService.createSubject(subject1)).thenReturn("sucess");
		
		this.mockMvc.perform(post("/subjects").contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andDo(print()).andExpect(status().isCreated());
	}
	
	@Test
	void testUpdateSubject() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(this.subject1);
		
		when(this.subjectService.updateSubject(subject1)).thenReturn("sucess");
		
		this.mockMvc.perform(put("/subjects").contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testDeleteSubject() throws Exception {
		when(this.subjectService.deleteSubject(this.subject1.getId())).thenReturn("sucess");
		
		this.mockMvc.perform(delete("/subjects/math")).andDo(print()).andExpect(status().isNoContent());
	}
}
