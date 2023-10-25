package br.com.tcc.project.controller.mapper;

import br.com.tcc.project.command.RegisterProfessorAnalysis;
import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.controller.request.RegisterProfessorAnalysisRequest;
import br.com.tcc.project.domain.NotificationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Mapper(componentModel = "spring")
@Component
public interface RegisterProfessorAnalysisControllerMapper {
  int DAYS_FOR_NOTIFICATION = 7;

  @Mapping(target = "maximumDate", source = "source.dataMaxima")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "studentEmail", source = "emailAluno")
  @Mapping(target = "studentName", source = "nomeAluno")
  RegisterProfessorAnalysis.Request map(
      RegisterProfessorAnalysisRequest source,
      CollegeDocument collegeOriginDocument,
      CourseDocument courseOriginDocument,
      DisciplineDocument disciplineOriginDocument,
      CollegeDocument collegeDestinyDocument,
      CourseDocument courseDestinyDocument,
      DisciplineDocument disciplineDestinyDocument,
      ProfessorDocument professorDocument,
      UsuarioDocument adminUserDocument,
      String nomeAluno,
      String emailAluno,
      Integer id,
      String status);

  @Mapping(target = "maximumDate", expression = "java(convertData(dataMaxima))")
  @Mapping(target = "studentEmail", source = "studentEmail")
  @Mapping(target = "studentName", source = "studentName")
  @Mapping(target = "id", source = "id")
  RegisterProfessorAnalysis.Request map(
          Date dataMaxima,
          CollegeDocument collegeOriginDocument,
          CourseDocument courseOriginDocument,
          DisciplineDocument disciplineOriginDocument,
          CollegeDocument collegeDestinyDocument,
          CourseDocument courseDestinyDocument,
          DisciplineDocument disciplineDestinyDocument,
          ProfessorDocument professorDocument,
          UsuarioDocument adminUserDocument,
          Integer id,
          String studentEmail,
          String studentName,
          String status) throws ParseException;

  default String convertData(Date maximumDate) throws ParseException {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      return sdf.format(maximumDate);
    } catch (Exception e) {
      return "Date format error";
    }
  }

  @Mapping(target = "maximumDate", expression = "java(calculateDateForNotificationSevenDaysBeforeExpiration(maximumDate))")
  @Mapping(target = "analisesDocument", source = "analisesDocument")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "status", source = "status")
  @Mapping(target = "id", source = "id")
  RegisterProfessorNotification.Request map(
          AnalisesDocument analisesDocument,
          Date maximumDate,
          NotificationStatus status,
          Integer id,
          String email);

  default Date calculateDateForNotificationSevenDaysBeforeExpiration(Date maximumDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(maximumDate);

    calendar.add(Calendar.DAY_OF_MONTH, -DAYS_FOR_NOTIFICATION);

    return calendar.getTime();
  }
}
