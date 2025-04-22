package br.com.lucas.santos.case_itau_principal.adapters.repository;

import br.com.lucas.santos.case_itau_principal.entities.HistoryProposal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<HistoryProposal, String> {
}
