package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfessorAnalysis;
import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.domain.NotificationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

  @Mapping(target = "dataMaxima", source = "source.maximumDate")
  @Mapping(target = "status", expression = "java(getStatusName(source.status))")
  NotificationDocument map(RegisterProfessorNotification.Request source);

  default String getStatusName(NotificationStatus status) {
    return status.name();
  }

}
