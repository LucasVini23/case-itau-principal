package br.com.lucas.santos.case_itau_principal.adapters.repository;

import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBatchRepository extends JpaRepository<UserBatch, Long> {
}
