package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterOpenAIEquivalenceAnalysis;
import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.domain.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OpenAIEquivalenceAnalysisMapper {
  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "status", expression = "java(getStatusName(source.status))")
  @Mapping(target = "disciplinaOrigem", source = "source.originDisciplineDocument")
  @Mapping(target = "disciplinaDestino", source = "source.destinyDisciplineDocument")
  @Mapping(target = "semelhanca", source = "source.resemblance")
  @Mapping(target = "diferenca", source = "source.difference")
  @Mapping(target = "consideracao", source = "source.consideration")
  @Mapping(target = "dataCriacao", source = "source.createdAt")
  OpenAIEquivalenceAnalysisDocument map(RegisterOpenAIEquivalenceAnalysis.Request source);

  default String getStatusName(Status status) {
    return status.name();
  }

}
