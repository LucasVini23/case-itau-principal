package br.com.lucas.santos.case_itau_principal.service;

import br.com.lucas.santos.case_itau_principal.adapters.repository.HistoryRepository;
import br.com.lucas.santos.case_itau_principal.entities.Debt;
import br.com.lucas.santos.case_itau_principal.entities.HistoryProposal;
import br.com.lucas.santos.case_itau_principal.entities.Proposal;
import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import br.com.lucas.santos.case_itau_principal.infrastructure.MapProposal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PrincipalServiceTest {

    @InjectMocks
    private PrincipalService service;

    @Mock
    private MapProposal mapProposal;
    @Mock
    private HistoryRepository historyRepository;

    @Test
    void testValidateDebtScoreGreaterThan600() {
        UserBatch user = userBuilder();
        user.setScoreCredit(700);

        Mockito.when(mapProposal.toNotification(user)).thenReturn(new HistoryProposal());
        Mockito.when(historyRepository.save(new HistoryProposal())).thenReturn(new HistoryProposal());

        service.validateDebt(user);

        assertNotNull(user.getProposal());
    }

    @Test
    void testValidateDebtScoreBelow600() {
        UserBatch user = userBuilder();

        Mockito.when(mapProposal.toNotification(user)).thenReturn(new HistoryProposal());
        Mockito.when(historyRepository.save(new HistoryProposal())).thenReturn(new HistoryProposal());

        service.validateDebt(user);

        assertNotNull(user.getProposal());
    }

    @Test
    void testValidateDebtScoreBelow400() {
        UserBatch user = userBuilder();
        user.setScoreCredit(300);

        Mockito.when(mapProposal.toNotification(user)).thenReturn(new HistoryProposal());
        Mockito.when(historyRepository.save(new HistoryProposal())).thenReturn(new HistoryProposal());

        service.validateDebt(user);

        assertNull(user.getProposal());
    }

    @Test
    void testeValidateAcceptProposalAccepted() {
        UserBatch user = userBuilder();
        user.setProposal(proposalBuilder());
        user.getProposal().setAccept(true);
        UserBatch userBatch = service.validateAcceptProposal(user);

        assertEquals(user.getDebt().getValueDebt(), userBatch.getProposal().getFinalValue());
    }

    @Test
    void testeValidateAcceptProposalNotAccepted() {
        UserBatch user = userBuilder();
        user.setProposal(proposalBuilder());
        UserBatch userBatch = service.validateAcceptProposal(user);

        assertNotEquals(user.getDebt().getValueDebt(), userBatch.getProposal().getFinalValue());
        assertEquals(user, userBatch);
    }

    private UserBatch userBuilder(){
        return UserBatch.builder()
                .name("Lucas")
                .document("12345678910")
                .email("Lucas@gmail.com")
                .phones(List.of("11111111111", "999999999"))
                .addresses(List.of("Rua", "Avenida"))
                .status(null)
                .dueDate(LocalDate.now().minusDays(1))
                .notification(LocalDate.now())
                .debt(Debt.builder().debtMaturity(LocalDate.now().minusDays(1)).valueDebt(200.0).debt("Banco").build())
                .build();
    }

    private Proposal proposalBuilder() {
        return Proposal.builder()
                .debt("Banco")
                .originalDebt(200.0)
                .discount(0.20)
                .installments(4)
                .installmentsValue(160.0 / 4)
                .finalValue(160.0).build();
    }
}