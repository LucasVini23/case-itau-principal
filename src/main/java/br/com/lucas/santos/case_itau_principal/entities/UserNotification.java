package br.com.lucas.santos.case_itau_principal.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotification {

    private String name;
    private String document;
    private String email;
    private List<String> phones;
    private List<String> addresses;
    private String status;
    private LocalDate dueDate;
}
