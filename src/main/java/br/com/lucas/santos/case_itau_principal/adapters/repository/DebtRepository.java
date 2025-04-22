package br.com.lucas.santos.case_itau_principal.adapters.repository;

import br.com.lucas.santos.case_itau_principal.entities.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {
}
