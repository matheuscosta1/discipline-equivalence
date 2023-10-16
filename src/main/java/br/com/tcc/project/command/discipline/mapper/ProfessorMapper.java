package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "faculdade", source = "source.collegeDocument")
  @Mapping(target = "curso", source = "source.courseDocument")
  @Mapping(target = "disciplina", source = "source.disciplineDocument")
  @Mapping(target = "usuario", source = "source.usuarioDocument")
  ProfessorDocument map(RegisterProfessor.Request source);
}
