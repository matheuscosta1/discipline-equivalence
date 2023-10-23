package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.mapper.ProfessorDocumentMapper;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.controller.mapper.RegisterProfessorControllerMapper;
import br.com.tcc.project.controller.request.RegisterProfessorRequest;
import br.com.tcc.project.domain.Profile;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  private Random random = new Random();

  @Operation(summary = "Register new professor", description = "Register new professor")
  @DocApiResponsesError
  @PostMapping("professores")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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

    UsuarioDocument usuarioDocument = commandGateway.invoke(
            RegisterUser.class,
            RegisterUser
                    .Request
                    .builder()
                    .nome(request.getNome())
                    .email(request.getEmail())
                    .password(bCryptPasswordEncoder.encode(newPassword()))
                    .perfil(Profile.PROFESSOR.getCode())
                    .build());

    commandGateway.invoke(RegisterProfile.class, RegisterProfile.Request.builder().usuario(usuarioDocument).perfil(Profile.PROFESSOR.getCode()).id(null).build());


    ProfessorDocument professorDocument =
        commandGateway.invoke(
            RegisterProfessor.class,
            mapper.map(request, collegeDocument, courseDocument, disciplineDocument, usuarioDocument, null));

    return ResponseEntity.ok(professorDocumentMapper.map(professorDocument));
  }

  @Operation(summary = "Find discipline by id", description = "Find discipline by id")
  @DocApiResponsesError
  @GetMapping("professores/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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

    UsuarioDocument usuarioDocument = commandGateway.invoke(
            RegisterUser.class,
            RegisterUser.Request
                    .builder()
                    .nome(request.getNome())
                    .email(request.getEmail())
                    .password(bCryptPasswordEncoder.encode(newPassword()))
                    .perfil(Profile.PROFESSOR.getCode())
                    .build()
    );

    commandGateway.invoke(RegisterProfile.class, RegisterProfile.Request.builder().usuario(usuarioDocument).perfil(Profile.PROFESSOR.getCode()).id(null).build());

    ProfessorDocument professorDocument =
            commandGateway.invoke(
                    RegisterProfessor.class,
                    mapper.map(request, collegeDocument, courseDocument, disciplineDocument, usuarioDocument, id));

    return ResponseEntity.ok(professorDocumentMapper.map(professorDocument));
  }

  @Operation(summary = "Deleta professor", description = "Deleta professor")
  @DocApiResponsesError
  @DeleteMapping("professores/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<CollegeDocument> deleteAnaliseById(
          @PathVariable(value = "id") Integer id) {
    commandGateway.invoke(
            DeleteById.class,
            DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.ProfessorDocument.class).id(id).build());
    return null;
  }

  private String newPassword() {
    char[] vector = new char[10];
    for(int i=0; i<10; i++){
      vector[i] = randomChar();
    }
    return new String(vector);
  }

  private char randomChar() {
    int option = random.nextInt(3);
    if(option == 0) {
      return (char) (random.nextInt(10) + 48);
    }
    else if(option == 1){
      return (char) (random.nextInt(26) + 65);
    }
    else {
      return (char) (random.nextInt(26) + 97);
    }
  }
}
