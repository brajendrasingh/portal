package com.bksoft.questionbank.repositories.i18n;

import com.bksoft.questionbank.entities.i18n.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, String> {
}
