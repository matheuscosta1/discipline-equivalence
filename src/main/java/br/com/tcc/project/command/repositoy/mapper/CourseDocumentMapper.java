package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.domain.Discipline;
import br.com.tcc.project.response.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseDocumentMapper {

  List<CourseResponse> map(List<CourseDocument> course);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.name")
  @Mapping(target = "faculdadeId", source = "source.college.id")
  CourseResponse map(CourseDocument source);
}
