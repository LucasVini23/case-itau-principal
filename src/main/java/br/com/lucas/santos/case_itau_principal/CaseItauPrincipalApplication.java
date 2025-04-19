package br.com.lucas.santos.case_itau_principal;

import br.com.lucas.santos.case_itau_principal.entity.UserBatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CaseItauPrincipalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CaseItauPrincipalApplication.class, args);
	}

	@Autowired
	private SqsTemplate sqsTemplate;

	@Override
	public void run(String... args) throws Exception {
		UserBatch user = UserBatch.builder()
				.name("LUCAS")
				.email("LUCAS@GMAIL.COM")
				.phones(List.of("11", "22"))
				.addresses(List.of("RUA", "BAIRRO", "AVENIDA"))
				.status("Normal")
				.notification(LocalDate.now()).build();
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String s = mapper.writeValueAsString(user);
		sqsTemplate.send("fila-appNotification", s);
		System.out.println(s);
	}
}
