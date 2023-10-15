package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.controller.request.RegisterProfessorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterProfessorControllerMapper {

  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "email", source = "source.email")
  @Mapping(target = "id", source = "id")
  RegisterProfessor.Request map(
          RegisterProfessorRequest source,
          CollegeDocument collegeDocument,
          CourseDocument courseDocument,
          DisciplineDocument disciplineDocument, UserDocument userDocument, Integer id);
}
