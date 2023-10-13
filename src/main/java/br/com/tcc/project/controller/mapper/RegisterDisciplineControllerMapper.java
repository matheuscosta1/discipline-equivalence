package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.controller.request.RegisterDisciplineRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterDisciplineControllerMapper {
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "source.nome")
  @Mapping(target = "workLoad", source = "source.cargaHoraria")
  @Mapping(target = "originCode", source = "source.codigoOrigem")
  @Mapping(target = "program", source = "source.programa")
  @Mapping(target = "menu", source = "source.ementa")
  @Mapping(target = "collegeDocument", source = "collegeDocument")
  @Mapping(target = "courseDocument", source = "courseDocument")
  RegisterDiscipline.Request map(
      RegisterDisciplineRequest source,
      CollegeDocument collegeDocument,
      CourseDocument courseDocument,
      Integer id);
}
