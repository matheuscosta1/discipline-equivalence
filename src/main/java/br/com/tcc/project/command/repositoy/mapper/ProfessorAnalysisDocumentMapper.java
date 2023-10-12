package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.response.ProfessorAnaliseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorAnalysisDocumentMapper {

  List<ProfessorAnaliseResponse> map(List<AnalisesDocument> source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "faculdadeOrigemId", source = "source.faculdadeOrigem.id")
  @Mapping(target = "cursoOrigemId", source = "source.cursoOrigem.id")
  @Mapping(target = "disciplinaOrigemId", source = "source.disciplinaOrigem.id")

  @Mapping(target = "faculdadeDestinoId", source = "source.faculdadeDestino.id")
  @Mapping(target = "cursoDestinoId", source = "source.cursoDestino.id")
  @Mapping(target = "disciplinaDestinoId", source = "source.disciplinaDestino.id")


  @Mapping(target = "professorId", source = "source.professor.id")
  @Mapping(target = "nomeProfessor", source = "source.professor.nome")

  @Mapping(target = "nomeFaculdadeOrigem", source = "source.faculdadeOrigem.nome")
  @Mapping(target = "nomeCursoOrigem", source = "source.cursoOrigem.nome")
  @Mapping(target = "nomeDisciplinaOrigem", source = "source.disciplinaOrigem.nome")

  @Mapping(target = "nomeFaculdadeDestino", source = "source.faculdadeDestino.nome")
  @Mapping(target = "nomeCursoDestino", source = "source.cursoDestino.nome")
  @Mapping(target = "nomeDisciplinaDestino", source = "source.disciplinaDestino.nome")
  @Mapping(target = "dataMaxima", source = "source.dataMaxima")
  ProfessorAnaliseResponse map(AnalisesDocument source);
}