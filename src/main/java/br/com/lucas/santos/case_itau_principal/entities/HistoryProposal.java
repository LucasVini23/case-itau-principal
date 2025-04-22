package br.com.lucas.santos.case_itau_principal.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("historyProposal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryProposal {

    @Id
    private String document;
    private String debt;
    private Double originalDebt;
    private Double discount;
    private Integer installments;
    private Double installmentsValue;
    private Double finalValue;
    private String accept;

}
