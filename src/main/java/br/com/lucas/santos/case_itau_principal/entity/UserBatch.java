package br.com.lucas.santos.case_itau_principal.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBatch {

    private String name;
    private String document;
    private String email;
    private List<String> phones;
    private List<String> addresses;
    private String status;
    private LocalDate dueDate;
    private LocalDate notification;
}
