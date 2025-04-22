package br.com.lucas.santos.case_itau_principal.infrastructure;


import br.com.lucas.santos.case_itau_principal.entities.HistoryProposal;
import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapProposal {

    @Mapping(source = "document", target = "document")
    @Mapping(source = "proposal.debt", target = "debt")
    @Mapping(source = "proposal.originalDebt", target = "originalDebt")
    @Mapping(source = "proposal.discount", target = "discount")
    @Mapping(source = "proposal.installments", target = "installments")
    @Mapping(source = "proposal.installmentsValue", target = "installmentsValue")
    @Mapping(source = "proposal.finalValue", target = "finalValue")
    @Mapping(source = "proposal.accept", target = "accept")
    HistoryProposal toNotification(UserBatch user);
}
