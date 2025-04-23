package br.com.lucas.santos.case_itau_principal.service;

import br.com.lucas.santos.case_itau_principal.entities.Payment;
import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SqsListeners {

    private final ObjectMapper mapper;
    private final SendQueueNotification notification;
    private final SendQueuePayment payment;
    private final PrincipalService service;
//    private final UserBatchRepository repository;

    @SqsListener("fila-appBatch")
    public void listenerBatch(String message) {
        mapper.findAndRegisterModules();
        try {
            UserBatch user = mapper.readValue(message, UserBatch.class);

            notification.sendAnotherApi(user);
            if (user.isRenegotiate()) {
                service.validateDebt(user);
                if (user.getProposal() != null) {
                    service.validateAcceptProposal(user);
                    payment.sendAnotherApi(user);
                    if (user.getDueDate().isBefore(LocalDate.now()) && !user.getPayment().getPaid().equals("PAGO")) {
                        user.setStatus("Inadimplente");
                        log.info("Enviado para aplicação de negativação");
                    }
                    //        repository.save(user); Update no usuario
                }
            }
            log.info("Terminou todo o processo do cliente: {}", user.getName());
        }
        catch (JsonProcessingException exception) {
            throw new RuntimeException("Erro na conversão do JSON ", exception);
        }
    }

    @SqsListener("fila-appProcessedPayment")
    private void listenPayment(String message) throws JsonProcessingException {
        Payment payment = mapper.readValue(message, Payment.class);
        /*Recuperar o Usuario atualizado do banco
        Atualizar com o pagamento vindo da app de pagamento
        Validar Inadimplencia
        E Atualizar na base o usuario
        */
    }

}
