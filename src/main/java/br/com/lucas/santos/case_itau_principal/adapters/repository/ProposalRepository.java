package br.com.lucas.santos.case_itau_principal.adapters.repository;

import br.com.lucas.santos.case_itau_principal.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
