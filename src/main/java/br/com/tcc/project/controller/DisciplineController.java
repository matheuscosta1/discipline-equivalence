package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.controller.mapper.RegisterDisciplineControllerMapper;
import br.com.tcc.project.controller.request.FindDisciplineByCollegeAndCourseRequest;
import br.com.tcc.project.controller.request.RegisterDisciplineRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.DisciplineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Discipline")
@RestController
@Slf4j
@Validated
public class DisciplineController {

  @Autowired @Setter private RegisterDisciplineControllerMapper registerDisciplineMapper;

  @Autowired @Setter private CommandGateway commandGateway;

  @Operation(summary = "Register new discipline", description = "Register new discipline")
  @DocApiResponsesError
  @PostMapping("register-discipline")
  public ResponseEntity<Void> registerDiscipline(
      @Valid @RequestBody RegisterDisciplineRequest request) {

    CollegeDocument collegeDocument =
            commandGateway.invoke(
                    FindCollegeById.class,
                    FindCollegeById.Request.builder().collegeId(request.getCollegeId()).build());

    CourseDocument courseDocument = commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().courseId(request.getCourseId()).build());

    commandGateway.invoke(RegisterDiscipline.class, registerDisciplineMapper.map(request, collegeDocument, courseDocument));

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Find discipline by code", description = "Find discipline by code")
  @DocApiResponsesError
  @GetMapping("find-discipline")
  public ResponseEntity<DisciplineResponse> findDisciplineByCode(
      @RequestParam(value = "disciplineCode") String disciplineCode) {

    DisciplineResponse disciplineDocument =
        commandGateway.invoke(
            FindDisciplineByCode.class,
            FindDisciplineByCode.Request.builder().disciplineCode(disciplineCode).build());

    return ResponseEntity.ok(disciplineDocument);
  }

  @Operation(
      summary = "Find discipline by college and course",
      description = "Find discipline by college and course")
  @DocApiResponsesError
  @GetMapping("find-disciplines")
  public ResponseEntity<List<DisciplineResponse>> findAllDisciplineByCollegeAndCourse(
      @RequestBody FindDisciplineByCollegeAndCourseRequest request) {

    CollegeDocument collegeDocument =
            commandGateway.invoke(
                    FindCollegeById.class,
                    FindCollegeById.Request.builder().collegeId(request.getCollegeId()).build());

    CourseDocument courseDocument = commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().courseId(request.getCourseId()).build());

    List<DisciplineResponse> colleges =
        commandGateway.invoke(
            FindAllDisciplineByCollegeAndCourse.class,
            FindAllDisciplineByCollegeAndCourse.Request.builder()
                .collegeId(collegeDocument.getId())
                .courseId(courseDocument.getId())
                .build());

    return ResponseEntity.ok(colleges);
  }
}
