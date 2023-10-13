package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfessorAnalysis;
import br.com.tcc.project.command.repositoy.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorAnalysisMapper {

  @Mapping(target = "faculdadeOrigem", source = "source.collegeOriginDocument")
  @Mapping(target = "cursoOrigem", source = "source.courseOriginDocument")
  @Mapping(target = "disciplinaOrigem", source = "source.disciplineOriginDocument")
  @Mapping(target = "faculdadeDestino", source = "source.collegeDestinyDocument")
  @Mapping(target = "cursoDestino", source = "source.courseDestinyDocument")
  @Mapping(target = "disciplinaDestino", source = "source.disciplineDestinyDocument")
  @Mapping(target = "professor", source = "source.professorDocument")
  @Mapping(target = "dataMaxima", source = "source.maximumDate")
  AnalisesDocument map(RegisterProfessorAnalysis.Request source);
}
