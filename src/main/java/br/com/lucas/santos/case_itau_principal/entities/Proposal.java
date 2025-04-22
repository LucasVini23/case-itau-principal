package br.com.lucas.santos.case_itau_principal.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String debt;
    private Double originalDebt;
    private Double discount;
    private Integer installments;
    private Double installmentsValue;
    private Double finalValue;
    private boolean accept;

    @OneToOne
    private UserBatch user;
}
