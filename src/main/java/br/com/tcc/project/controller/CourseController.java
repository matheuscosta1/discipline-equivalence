package br.com.tcc.project.controller;

import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.command.FindAllCourse;
import br.com.tcc.project.command.FindAllCourseByCollege;
import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.mongo.model.CourseDocument;
import br.com.tcc.project.controller.mapper.RegisterDisciplineControllerMapper;
import br.com.tcc.project.controller.request.RegisterCourseRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
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

@Tag(name = "Course")
@RestController
@Slf4j
@Validated
public class CourseController {

  @Autowired @Setter private RegisterDisciplineControllerMapper registerDisciplineMapper;

  @Autowired @Setter private CommandGateway commandGateway;

  @Operation(summary = "Register new course", description = "Register new course")
  @DocApiResponsesError
  @PostMapping("register-course")
  public ResponseEntity<Void> registerCourse(@Valid @RequestBody RegisterCourseRequest request) {

    commandGateway.invoke(
        RegisterCourse.class,
        RegisterCourse.Request.builder()
            .name(request.getName())
            .collegeName(request.getCollegeName())
            .build());

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Find course by college id", description = "Find course by college id")
  @DocApiResponsesError
  @GetMapping("find-courses")
  public ResponseEntity<List<CourseDocument>> findAllCourse() {

    List<CourseDocument> courses =
        commandGateway.invoke(
            FindAllCourse.class, FindAllCourse.Request.builder().nothing("nothing").build());

    return ResponseEntity.ok(courses);
  }

  @Operation(summary = "Find course by college id", description = "Find course by college id")
  @DocApiResponsesError
  @GetMapping("find-courses-by-college")
  public ResponseEntity<List<CourseDocument>> findCoursesByCollegeName(
      @RequestParam String college) {

    List<CourseDocument> courses =
        commandGateway.invoke(
            FindAllCourseByCollege.class,
            FindAllCourseByCollege.Request.builder().collegeName(college).build());

    return ResponseEntity.ok(courses);
  }
}
