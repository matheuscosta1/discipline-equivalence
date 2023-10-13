package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.mapper.ProfessorDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.controller.mapper.RegisterProfessorControllerMapper;
import br.com.tcc.project.controller.request.RegisterProfessorRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.ProfessorResponse;
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

@Tag(name = "Professor")
@RestController
@Slf4j
@Validated
public class ProfessorController {

  @Autowired @Setter private CommandGateway commandGateway;
  private final RegisterProfessorControllerMapper mapper =
      Mappers.getMapper(RegisterProfessorControllerMapper.class);
  private final ProfessorDocumentMapper professorDocumentMapper =
      Mappers.getMapper(ProfessorDocumentMapper.class);

  @Operation(summary = "Register new professor", description = "Register new professor")
  @DocApiResponsesError
  @PostMapping("professores")
  public ResponseEntity<ProfessorResponse> registerProfessor(
      @Valid @RequestBody RegisterProfessorRequest request) {

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeId()).build());

    CourseDocument courseDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoId()).build());

    DisciplineDocument disciplineDocument =
        commandGateway.invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder().id(request.getDisciplinaId()).build());
    ProfessorDocument professorDocument =
        commandGateway.invoke(
            RegisterProfessor.class,
            mapper.map(request, collegeDocument, courseDocument, disciplineDocument, null));

    return ResponseEntity.ok(professorDocumentMapper.map(professorDocument));
  }

  @Operation(summary = "Find discipline by id", description = "Find discipline by id")
  @DocApiResponsesError
  @GetMapping("professores/{id}")
  public ResponseEntity<ProfessorResponse> findProfessorById(
      @PathVariable(value = "id") Integer id) {

    ProfessorDocument professorDocument =
        commandGateway.invoke(
            FindProfessorById.class, FindProfessorById.Request.builder().id(id).build());

    return ResponseEntity.ok(professorDocumentMapper.map(professorDocument));
  }

  @Operation(
      summary = "Find discipline by college and course",
      description = "Find discipline by college and course")
  @DocApiResponsesError
  @GetMapping("professores")
  public ResponseEntity<Page<ProfessorResponse>> findAllProfessorByDiscplineCourseAndFaculdadeId(
      @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
      @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
      @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
      @RequestParam(value = "nome", required = false) String nome,
      @RequestParam(value = "disciplinaId", required = false) String disciplinaId) {

    Page<ProfessorResponse> professorResponses =
        commandGateway.invoke(
            FindAllProfessor.class,
            FindAllProfessor.Request.builder()
                .pagina(pagina)
                .paginas(paginas)
                .orderBy(orderBy)
                .direction(direction)
                .nome(nome)
                .disciplinaId(disciplinaId)
                .build());

    return ResponseEntity.ok(professorResponses);
  }

  @Operation(summary = "Atualiza professor", description = "Atualiza professor")
  @DocApiResponsesError
  @PutMapping("professores/{id}")
  public ResponseEntity<ProfessorResponse> updateProfessor(@PathVariable(value = "id") Integer id,
          @Valid @RequestBody RegisterProfessorRequest request) {

    CollegeDocument collegeDocument =
            commandGateway.invoke(
                    FindCollegeById.class,
                    FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeId()).build());

    CourseDocument courseDocument =
            commandGateway.invoke(
                    FindCourseById.class,
                    FindCourseById.Request.builder().cursoId(request.getCursoId()).build());

    DisciplineDocument disciplineDocument =
            commandGateway.invoke(
                    FindDisciplineById.class,
                    FindDisciplineById.Request.builder().id(request.getDisciplinaId()).build());
    ProfessorDocument professorDocument =
            commandGateway.invoke(
                    RegisterProfessor.class,
                    mapper.map(request, collegeDocument, courseDocument, disciplineDocument, id));

    return ResponseEntity.ok(professorDocumentMapper.map(professorDocument));
  }

  // TODO: Implementar PUT e Delete Cursos

}
