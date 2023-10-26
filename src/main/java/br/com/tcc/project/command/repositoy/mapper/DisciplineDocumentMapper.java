package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.response.AnaliseEquivalenciaDisciplineResponse;
import br.com.tcc.project.response.DisciplineResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
  @Mapping(target = "nomeFaculdade", source = "source.faculdade.nome")
  @Mapping(target = "cursoId", source = "source.curso.id")
  @Mapping(target = "nomeCurso", source = "source.curso.nome")
  DisciplineResponse map(DisciplineDocument source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "codigoOrigem", source = "source.codigoOrigem")
  @Mapping(target = "ementa", source = "source.ementa")
  @Mapping(target = "programa", source = "source.programa")
  @Mapping(target = "cargaHoraria", source = "source.cargaHoraria")
  @Mapping(target = "faculdadeId", source = "source.faculdade.id")
  @Mapping(target = "cursoId", source = "source.curso.id")
  @Mapping(target = "nomeFaculdade", source = "source.faculdade.nome")
  @Mapping(target = "nomeCurso", source = "source.curso.nome")
  AnaliseEquivalenciaDisciplineResponse mapForResumo(DisciplineDocument source);
}
