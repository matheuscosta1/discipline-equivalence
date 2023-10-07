package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.response.CourseResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseDocumentMapper {

  List<CourseResponse> map(List<CourseDocument> course);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.name")
  @Mapping(target = "faculdadeId", source = "source.college.id")
  CourseResponse map(CourseDocument source);
}
