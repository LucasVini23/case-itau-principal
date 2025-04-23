package br.com.lucas.santos.case_itau_principal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long proposalId;
    private String cpf;
    private Double amount;
    private String method;
    private Integer installments;
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private CardUser card;
    private String paid;

//    @OneToOne
//    @JsonIgnore
//    private UserBatch user;

}
