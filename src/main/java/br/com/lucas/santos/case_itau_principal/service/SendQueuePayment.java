package br.com.lucas.santos.case_itau_principal.service;

import br.com.lucas.santos.case_itau_principal.entities.Payment;
import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import br.com.lucas.santos.case_itau_principal.entities.interfaces.SendExternalApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class SendQueuePayment implements SendExternalApi {

    private final SqsTemplate sqsTemplate;
    private final ObjectMapper mapper;

    @Override
    public void sendAnotherApi(UserBatch userBatch) {
        mapper.findAndRegisterModules();
        Payment payment = userBatch.getPayment();
        try {
            String paymentString = mapper.writeValueAsString(payment);
            sqsTemplate.send("fila-appPayment", paymentString);
            log.info("Enviado para a fila de pagamento cliente: {}", userBatch.getName());
        }
        catch (JsonProcessingException exception) {
            throw new RuntimeException("Problema no envio para a fila de pagamento", exception);
        }
    }
}
