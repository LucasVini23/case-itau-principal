package br.com.lucas.santos.case_itau_principal.service;

import br.com.lucas.santos.case_itau_principal.adapters.repository.DebtRepository;
import br.com.lucas.santos.case_itau_principal.adapters.repository.HistoryRepository;
import br.com.lucas.santos.case_itau_principal.adapters.repository.UserBatchRepository;
import br.com.lucas.santos.case_itau_principal.entities.Debt;
import br.com.lucas.santos.case_itau_principal.entities.HistoryProposal;
import br.com.lucas.santos.case_itau_principal.entities.Proposal;
import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import br.com.lucas.santos.case_itau_principal.infrastructure.MapProposal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class PrincipalService {

    private final UserBatchRepository repository;
    private final DebtRepository debtRepository;
    private final MapProposal mapProposal;
    private final HistoryRepository historyRepository;

    public void validateDebt(UserBatch user) {
        Debt debt = user.getDebt();
        if (debt.getDebtMaturity().isBefore(LocalDate.now())) {
                log.info("Divida em Atraso");
                Integer score = getScore(user);

                if (score < 400) {
                    System.out.println("No momento não é possivel ser feita uma renegociação");
                }
                if(score < 600) {
                    user.setProposal(calcuteProposal(4, 0.10, debt));
                }
                else {
                    user.setProposal(calcuteProposal(8, 0.20, debt));
                }
            }
        HistoryProposal historyProposal = mapProposal.toNotification(user);
        historyRepository.save(historyProposal);
//        debtRepository.save(debt); Update no debito
//        repository.save(user); Update no usuario
    }

    public UserBatch validateAcceptProposal(UserBatch user) {
        Proposal proposal = user.getProposal();
        if (proposal == null) return user;
        if (proposal.getDebt().equals(user.getDebt().getDebt()) && proposal.isAccept()) {
            user.getDebt().setValueDebt(proposal.getFinalValue());
        }
        return user;
    }

    private Integer getScore(UserBatch user) {
        Integer bureauScore;
        if (user.getScoreCredit() == null) {
            bureauScore = 500;
        }
        else {
            bureauScore = user.getScoreCredit();
        }
        //IF APENAS PARA PARAR O ERRO NO LOG, POIS SEMPRE O BUREAU VAI RETORNAR UM SCORE
          // uma logica para puxar de algum birô de crédito essa informação
        return bureauScore; // não retornei direto, só para deixar o nome da variavel
    }

    private Proposal calcuteProposal(Integer installments, Double discount, Debt debt) {
        double discountValue = debt.getValueDebt() * discount;
        double finalValue = debt.getValueDebt() - discountValue;
        return Proposal.builder()
                .debt(debt.getDebt())
                .originalDebt(debt.getValueDebt())
                .discount(discount)
                .installments(installments)
                .installmentsValue(finalValue / installments)
                .finalValue(finalValue)
                .accept(finalValue < 500) // finalidade de teste
                .build();
    }

}
