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

import java.util.Calendar;
import java.util.Date;

@Mapper(componentModel = "spring")
@Component
public interface RegisterProfessorAnalysisControllerMapper {
  int DAYS_FOR_NOTIFICATION = 7;

  @Mapping(target = "maximumDate", source = "source.dataMaxima")
  @Mapping(target = "id", source = "id")
  RegisterProfessorAnalysis.Request map(
      RegisterProfessorAnalysisRequest source,
      CollegeDocument collegeOriginDocument,
      CourseDocument courseOriginDocument,
      DisciplineDocument disciplineOriginDocument,
      CollegeDocument collegeDestinyDocument,
      CourseDocument courseDestinyDocument,
      DisciplineDocument disciplineDestinyDocument,
      ProfessorDocument professorDocument,
      Integer id);


  @Mapping(target = "maximumDate", expression = "java(calculateDateForNotificationSevenDaysBeforeExpiration(maximumDate))")
  @Mapping(target = "analisesDocument", source = "analisesDocument")
  @Mapping(target = "email", source = "email")
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
