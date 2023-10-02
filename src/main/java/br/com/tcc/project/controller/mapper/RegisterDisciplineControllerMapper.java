package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.controller.request.RegisterDisciplineRequest;
import br.com.tcc.project.controller.response.RegisterDisciplineResponse;
import br.com.tcc.project.domain.Discipline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterDisciplineControllerMapper {

  RegisterDiscipline.Request map(RegisterDisciplineRequest source);

  RegisterDisciplineResponse newCreditCardMapper(Discipline source);
}
