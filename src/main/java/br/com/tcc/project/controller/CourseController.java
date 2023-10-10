package br.com.tcc.project.controller;

import br.com.tcc.project.command.FindAllCourse;
import br.com.tcc.project.command.FindAllCourseByCollege;
import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.controller.mapper.RegisterDisciplineControllerMapper;
import br.com.tcc.project.controller.request.RegisterCourseRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.CourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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

  @Autowired @Setter private RegisterDisciplineControllerMapper registerDisciplineMapper;

  @Autowired @Setter private CommandGateway commandGateway;

  @Operation(summary = "Register new course", description = "Register new course")
  @DocApiResponsesError
  @PostMapping("cursos")
  public ResponseEntity<Void> registerCourse(@Valid @RequestBody RegisterCourseRequest request) {

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().collegeId(request.getCollegeId()).build());

    commandGateway.invoke(
        RegisterCourse.class,
        RegisterCourse.Request.builder()
            .name(request.getName())
            .collegeDocument(collegeDocument)
            .build());

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Find course by college id", description = "Find course by college id")
  @DocApiResponsesError
  @GetMapping("cursos")
  public ResponseEntity<Page<CourseResponse>> findAllCourse(
      @RequestParam(value="pagina", defaultValue="0", required = false) Integer pagina,
      @RequestParam(value="paginas", defaultValue="25", required = false) Integer paginas,
      @RequestParam(value="orderBy", defaultValue="nome", required = false) String orderBy,
      @RequestParam(value="direction", defaultValue="ASC", required = false) String direction,
      @RequestParam(value="nome", required = false) String nome,
      @RequestParam(value="faculdadeId", required = false) String faculdadeId
  ) {

    Page<CourseResponse> courses =
        commandGateway.invoke(
            FindAllCourse.class, FindAllCourse.Request.builder().pagina(pagina).paginas(paginas).orderBy(orderBy).direction(direction).nome(nome).faculdadeId(faculdadeId).build());

    return ResponseEntity.ok(courses);
  }

  @Operation(summary = "Find course by college id", description = "Find course by college id")
  @DocApiResponsesError
  @GetMapping("find-courses-by-college") //TODO: deletar esse controller porque foi feito a busca por faculdade como parametro no metodo de cima
  public ResponseEntity<List<CourseResponse>> findCoursesByCollegeName(
      @RequestParam String college) {

    List<CourseResponse> courses =
        commandGateway.invoke(
            FindAllCourseByCollege.class,
            FindAllCourseByCollege.Request.builder().faculdadeId(1).build());

    return ResponseEntity.ok(courses);
  }
}
