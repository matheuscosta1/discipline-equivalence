package br.com.tcc.project.command.repositoy.mapper;

import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.response.EquivalenceResponse;
import br.com.tcc.project.response.ProfessorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EquivalenceDocumentMapper {

  List<EquivalenceResponse> map(List<EquivalenceDocument> source);

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nomeDisciplinaOrigem", source = "source.analisesDocument.disciplinaOrigem.nome")
  @Mapping(target = "codigoDisciplinaOrigem", source = "source.analisesDocument.disciplinaOrigem.codigoOrigem")
  @Mapping(target = "faculdadeOrigem", source = "source.analisesDocument.faculdadeOrigem.nome")
  @Mapping(target = "cursoOrigem", source = "source.analisesDocument.cursoOrigem.nome")
  @Mapping(target = "nomeDisciplinaDestino", source = "source.analisesDocument.disciplinaDestino.nome")
  @Mapping(target = "codigoDisciplinaDestino", source = "source.analisesDocument.disciplinaDestino.codigoOrigem")
  @Mapping(target = "faculdadeDestino", source = "source.analisesDocument.faculdadeDestino.nome")
  @Mapping(target = "cursoDestino", source = "source.analisesDocument.cursoDestino.nome")
  @Mapping(target = "nomeProfessor", source = "source.analisesDocument.professor.nome")
  @Mapping(target = "equivalente", expression = "java(mapEquivalence(source.equivalente))")
  @Mapping(target = "dataCriacao", expression = "java(convertData(source.dataCriacao))")
  @Mapping(target = "justificativa", source = "source.justificativa")
  @Mapping(target = "status", expression = "java(mapStatus(source))")
  EquivalenceResponse map(EquivalenceDocument source);

  default String mapEquivalence(Boolean equivalence) {
    return equivalence ? "EQUIVALENTE" : "NÃO EQUIVALENTE";
  }

  default String mapStatus(EquivalenceDocument source) {
    if(Status.MENU_CHANGE.name().equals(source.getStatus())) {
      return "MUDANÇA EMENTA";
    }
    return source.getEquivalente() ? "EQUIVALENTE" : "NÃO EQUIVALENTE";
  }

  default String convertData(Date maximumDate) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      return sdf.format(maximumDate);
    } catch (Exception e) {
      return "Date format error";
    }
  }
}
