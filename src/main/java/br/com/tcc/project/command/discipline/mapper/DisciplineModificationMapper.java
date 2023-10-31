package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.RegisterDisciplineModification;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplineModificationMapper {

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "status", source = "source.status")
  @Mapping(target = "disciplina", source = "source.disciplineDocument")
  DisciplineModificationDocument map(RegisterDisciplineModification.Request source);
}
