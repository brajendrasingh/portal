package com.bksoft.questionbank.repositories;

import com.bksoft.questionbank.entities.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionEntityRepository extends MongoRepository<QuestionEntity, String> {

}
