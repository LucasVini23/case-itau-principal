package br.com.lucas.santos.case_itau_principal.adapters.repository;

import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserBatchMongoRepository extends MongoRepository<UserBatch, Long> {
}
