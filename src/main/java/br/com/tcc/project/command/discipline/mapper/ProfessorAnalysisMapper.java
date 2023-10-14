package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfessorAnalysis;
import br.com.tcc.project.command.repositoy.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface ProfessorAnalysisMapper {

  @Mapping(target = "faculdadeOrigem", source = "source.collegeOriginDocument")
  @Mapping(target = "cursoOrigem", source = "source.courseOriginDocument")
  @Mapping(target = "disciplinaOrigem", source = "source.disciplineOriginDocument")
  @Mapping(target = "faculdadeDestino", source = "source.collegeDestinyDocument")
  @Mapping(target = "cursoDestino", source = "source.courseDestinyDocument")
  @Mapping(target = "disciplinaDestino", source = "source.disciplineDestinyDocument")
  @Mapping(target = "professor", source = "source.professorDocument")
  @Mapping(target = "dataMaxima", expression = "java(convertData(source.maximumDate))")
  AnalisesDocument map(RegisterProfessorAnalysis.Request source);

  default Date convertData(String maximumDate) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(sdf.parse(maximumDate).getTime());
        return new Date(date.getTime());
      } catch (Exception e) {
        return Date.from(Instant.now());
      }
  }
}
