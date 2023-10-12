package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.RegisterProfessorAnalysis;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.controller.request.RegisterProfessorAnalysisRequest;
import br.com.tcc.project.controller.request.RegisterProfessorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterProfessorAnalysisControllerMapper {

  @Mapping(target = "maximumDate", source = "source.dataMaxima")
  RegisterProfessorAnalysis.Request map(RegisterProfessorAnalysisRequest source, CollegeDocument collegeOriginDocument, CourseDocument courseOriginDocument, DisciplineDocument disciplineOriginDocument, CollegeDocument collegeDestinyDocument, CourseDocument courseDestinyDocument, DisciplineDocument disciplineDestinyDocument, ProfessorDocument professorDocument);
}
