package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

  @Mapping(target = "name", source = "source.name")
  @Mapping(target = "college", source = "source.collegeDocument")
  @Mapping(target = "course", source = "source.courseDocument")
  DisciplineDocument map(RegisterDiscipline.Request source);
}
