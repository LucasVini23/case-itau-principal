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

@Entity(name = "debtTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String debt;
    private Double valueDebt;
    private LocalDate debtMaturity;

//    @OneToOne
//    @JsonIgnore
//    private UserBatch user;


}
