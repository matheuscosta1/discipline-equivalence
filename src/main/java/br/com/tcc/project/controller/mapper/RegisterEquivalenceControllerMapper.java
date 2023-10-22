package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterEquivalence;
import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.controller.request.RegisterEquivalenceRequest;
import br.com.tcc.project.controller.request.RegisterProfessorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterEquivalenceControllerMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "justification", source = "source.justificativa")
  @Mapping(target = "equivalent", source = "source.equivalente")
  @Mapping(target = "analisesDocument", source = "analisesDocument")
  RegisterEquivalence.Request map(
          RegisterEquivalenceRequest source,
          AnalisesDocument analisesDocument, Integer id);
}