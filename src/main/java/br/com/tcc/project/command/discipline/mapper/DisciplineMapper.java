package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

  @Mapping(target = "nome", source = "source.name")
  @Mapping(target = "faculdade", source = "source.collegeDocument")
  @Mapping(target = "curso", source = "source.courseDocument")
  @Mapping(target = "programa", source = "source.program")
  @Mapping(target = "ementa", source = "source.menu")
  @Mapping(target = "codigoOrigem", source = "source.originCode")
  @Mapping(target = "cargaHoraria", source = "source.workLoad")
  DisciplineDocument map(RegisterDiscipline.Request source);
}
