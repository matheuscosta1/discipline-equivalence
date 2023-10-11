package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterCourseMapper {

  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "faculdade", source = "source.documentoFaculdade")
  CourseDocument map(RegisterCourse.Request source);
}
