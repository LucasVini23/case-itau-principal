package br.com.lucas.santos.case_itau_principal.infrastructure;


import br.com.lucas.santos.case_itau_principal.entities.UserBatch;
import br.com.lucas.santos.case_itau_principal.entities.UserNotification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapUserNotification {

    UserNotification toNotification(UserBatch user);
}
