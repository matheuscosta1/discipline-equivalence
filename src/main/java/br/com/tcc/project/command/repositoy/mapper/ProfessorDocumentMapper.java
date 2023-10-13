package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.response.ProfessorResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorDocumentMapper {

  List<ProfessorResponse> map(List<ProfessorDocument> source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "faculdadeId", source = "source.faculdade.id")
  @Mapping(target = "cursoId", source = "source.curso.id")
  @Mapping(target = "disciplinaId", source = "source.disciplina.id")
  @Mapping(target = "nomeFaculdade", source = "source.faculdade.nome")
  @Mapping(target = "nomeCurso", source = "source.curso.nome")
  @Mapping(target = "nomeDisciplina", source = "source.disciplina.nome")
  ProfessorResponse map(ProfessorDocument source);
}
