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
  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "codigoOrigem", source = "source.codigoOrigem")
  @Mapping(target = "ementa", source = "source.ementa")
  @Mapping(target = "programa", source = "source.programa")
  @Mapping(target = "cargaHoraria", source = "source.cargaHoraria")
  @Mapping(target = "faculdadeId", source = "source.faculdade.id")
  @Mapping(target = "cursoId", source = "source.curso.id")
  DisciplineResponse map(DisciplineDocument source);
}
