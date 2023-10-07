package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.controller.request.RegisterDisciplineRequest;
import br.com.tcc.project.controller.response.RegisterDisciplineResponse;
import br.com.tcc.project.domain.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterDisciplineControllerMapper {
  @Mapping(target = "name", source = "source.name")
  @Mapping(target = "workLoad", source = "source.workLoad")
  @Mapping(target = "originCode", source = "source.originCode")
  @Mapping(target = "program", source = "source.program")
  @Mapping(target = "menu", source = "source.menu")
  @Mapping(target = "collegeDocument", source = "collegeDocument")
  @Mapping(target = "courseDocument", source = "courseDocument")
  RegisterDiscipline.Request map(RegisterDisciplineRequest source, CollegeDocument collegeDocument, CourseDocument courseDocument);
}
