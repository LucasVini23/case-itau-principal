package br.com.lucas.santos.case_itau_principal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "debt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String debt;
    private Double valueDebt;
    private LocalDate debtMaturity;

    @OneToOne
    @JsonIgnore
    private UserBatch user;


}
