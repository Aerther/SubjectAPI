package com.example.subject_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subject_api.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, String> {}