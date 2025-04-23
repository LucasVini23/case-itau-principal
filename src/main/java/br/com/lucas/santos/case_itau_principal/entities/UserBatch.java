package br.com.lucas.santos.case_itau_principal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


//@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    private String email;
    private List<String> phones;
    private List<String> addresses;
    private String status;
    private LocalDate dueDate;
    private LocalDate notification;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Debt debt;
    private Integer scoreCredit;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Proposal proposal;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Payment payment;
    private boolean renegotiate;
}
