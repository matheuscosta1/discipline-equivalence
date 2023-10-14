package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.controller.request.RegisterCourseRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.CourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course")
@RestController
@Slf4j
@Validated
public class CourseController {

  @Autowired @Setter private CommandGateway commandGateway;
  private final CourseDocumentMapper courseDocumentMapper =
      Mappers.getMapper(CourseDocumentMapper.class);

  @Operation(summary = "Register new course", description = "Register new course")
  @DocApiResponsesError
  @PostMapping("cursos")
  public ResponseEntity<CourseResponse> registerCourse(
      @Valid @RequestBody RegisterCourseRequest request) {

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeId()).build());

    CourseResponse courseResponse =
        commandGateway.invoke(
            RegisterCourse.class,
            RegisterCourse.Request.builder()
                .nome(request.getNome())
                .documentoFaculdade(collegeDocument)
                .build());

    return ResponseEntity.ok(courseResponse);
  }

  @Operation(summary = "Find course by college id", description = "Find course by college id")
  @DocApiResponsesError
  @GetMapping("cursos")
  public ResponseEntity<Page<CourseResponse>> findAllCourse(
      @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
      @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
      @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
      @RequestParam(value = "nome", required = false) String nome,
      @RequestParam(value = "faculdadeId", required = false) String faculdadeId) {

    Page<CourseResponse> courses =
        commandGateway.invoke(
            FindAllCourse.class,
            FindAllCourse.Request.builder()
                .pagina(pagina)
                .paginas(paginas)
                .orderBy(orderBy)
                .direction(direction)
                .nome(nome)
                .faculdadeId(faculdadeId)
                .build());

    return ResponseEntity.ok(courses);
  }

  @Operation(summary = "Find course by college id", description = "Find course by college id")
  @DocApiResponsesError
  @GetMapping("cursos/{id}")
  public ResponseEntity<CourseResponse> findCourseById(@PathVariable(value = "id") Integer id) {

    CourseDocument courseDocument =
        commandGateway.invoke(
            FindCourseById.class, FindCourseById.Request.builder().cursoId(id).build());

    return ResponseEntity.ok(courseDocumentMapper.map(courseDocument));
  }

  @Operation(summary = "Register new course", description = "Register new course")
  @DocApiResponsesError
  @PutMapping("cursos/{id}")
  public ResponseEntity<CourseResponse> updateCourse(
      @PathVariable(value = "id") Integer id, @Valid @RequestBody RegisterCourseRequest request) {

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeId()).build());

    CourseResponse courseResponse =
        commandGateway.invoke(
            RegisterCourse.class,
            RegisterCourse.Request.builder()
                .id(id)
                .nome(request.getNome())
                .documentoFaculdade(collegeDocument)
                .build());

    return ResponseEntity.ok(courseResponse);
  }

  @Operation(summary = "Deleta curso", description = "Deleta curso")
  @DocApiResponsesError
  @DeleteMapping("cursos/{id}")
  public ResponseEntity<CollegeDocument> deleteCourseById(
          @PathVariable(value = "id") Integer id) {
    commandGateway.invoke(
            DeleteById.class,
            DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.CourseDocument.class).id(id).build());
    return null;
  }

}
