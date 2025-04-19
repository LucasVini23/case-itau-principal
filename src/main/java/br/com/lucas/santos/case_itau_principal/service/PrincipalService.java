package br.com.lucas.santos.case_itau_principal.service;

import br.com.lucas.santos.case_itau_principal.entity.UserBatch;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {

    private int a = 0;
    @Autowired
    private SqsTemplate sqsTemplate;

    @SqsListener("fila-appBatch")
    public void listen(UserBatch user) {
        if (a == 0) {
            System.out.println("messagem recebida: " + user);
        }
    }

    public void notificationClient(UserBatch user) {
        //pega o listen

//        if()
        sqsTemplate.send("fila-notification", user);
    }


}
