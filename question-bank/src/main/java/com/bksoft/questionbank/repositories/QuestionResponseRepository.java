package com.bksoft.questionbank.repositories;

import com.bksoft.questionbank.entities.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {
}
