package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.response.CourseResponse;
import br.com.tcc.project.response.DisciplineResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplineDocumentMapper {

  List<DisciplineResponse> map(List<DisciplineDocument> source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.name")
  @Mapping(target = "codigoOrigem", source = "source.originCode")
  @Mapping(target = "ementa", source = "source.menu")
  @Mapping(target = "programa", source = "source.program")
  @Mapping(target = "cargaHoraria", source = "source.workLoad")
  @Mapping(target = "faculdadeId", source = "source.college.id")
  @Mapping(target = "cursoId", source = "source.course.id")
  DisciplineResponse map(DisciplineDocument source);
}
