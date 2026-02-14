package com.bksoft.questionbank.repositories.i18n;

import com.bksoft.questionbank.entities.i18n.QuestionTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionTranslationRepository extends JpaRepository<QuestionTranslation, Long> {
    @Query("""
                SELECT DISTINCT qt
                FROM QuestionTranslation qt
                LEFT JOIN FETCH qt.options
                WHERE qt.question.id = :questionId
                AND qt.language = :lang
            """)
    Optional<QuestionTranslation> findByQuestionIdAndLanguage(@Param("questionId") String questionId, @Param("lang") String lang);

    @Query("""
                SELECT DISTINCT qt
                FROM QuestionTranslation qt
                LEFT JOIN FETCH qt.options
                JOIN FETCH qt.question q
                WHERE qt.language = :lang
            """)
    List<QuestionTranslation> findAllByLanguage(@Param("lang") String lang);
}

