package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterEquivalence;
import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface EquivalenceMapper {

  @Mapping(target = "justificativa", source = "source.justification")
  @Mapping(target = "equivalente", source = "source.equivalent")
  @Mapping(target = "analisesDocument", source = "source.analisesDocument")
  @Mapping(target = "dataCriacao", expression = "java(getLocalDate())")
  @Mapping(target = "status", source = "source.status")
  EquivalenceDocument map(RegisterEquivalence.Request source);

  default Date getLocalDate() {
    return new Date();
  }
}
