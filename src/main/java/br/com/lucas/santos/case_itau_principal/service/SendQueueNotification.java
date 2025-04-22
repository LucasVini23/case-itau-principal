package br.com.lucas.santos.case_itau_principal.service;

import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import br.com.lucas.santos.case_itau_principal.entities.UserNotification;
import br.com.lucas.santos.case_itau_principal.entities.interfaces.SendExternalApi;
import br.com.lucas.santos.case_itau_principal.infrastructure.MapUserNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendQueueNotification implements SendExternalApi {

    private final SqsTemplate sqsTemplate;
    private final MapUserNotification mapUserNotification;
    private final ObjectMapper mapper;

    private String convertToUserNotification(UserBatch user) throws JsonProcessingException {
        mapper.findAndRegisterModules();
        UserNotification userNotification = mapUserNotification.toNotification(user);
        return mapper.writeValueAsString(userNotification);
    }

    @Override
    public void sendAnotherApi(UserBatch userBatch) {
        try {
            sqsTemplate.send("fila-appNotification", convertToUserNotification(userBatch));
            log.info("Enviado para a fila de notificação cliente: {}", userBatch.getName());
        }
        catch (JsonProcessingException exception) {
            throw new RuntimeException("Problema no envio para a fila de notificação", exception);
        }
    }
}
