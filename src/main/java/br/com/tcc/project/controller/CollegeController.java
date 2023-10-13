package br.com.tcc.project.controller;

import br.com.tcc.project.command.DeleteById;
import br.com.tcc.project.command.FindAllCollege;
import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.RegisterCollege;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.controller.mapper.RegisterDisciplineControllerMapper;
import br.com.tcc.project.controller.request.RegisterCollegeRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "College")
@RestController
@Slf4j
@Validated
public class CollegeController {

  @Autowired @Setter private RegisterDisciplineControllerMapper registerDisciplineMapper;

  @Autowired @Setter private CommandGateway commandGateway;

  @Operation(summary = "Register new college", description = "Register new college")
  @DocApiResponsesError
  @PostMapping("faculdades")
  public ResponseEntity<CollegeDocument> registerCollege(
      @Valid @RequestBody RegisterCollegeRequest request) {

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            RegisterCollege.class,
            RegisterCollege.Request.builder().name(request.getNome()).build());

    return ResponseEntity.ok(collegeDocument);
  }

  @Operation(summary = "Find colleges", description = "Find all colleges")
  @DocApiResponsesError
  @GetMapping("faculdades")
  public ResponseEntity<Page<CollegeDocument>> findAllColleges(
      @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
      @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
      @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
      @RequestParam(value = "nome", required = false) String nome) {
    Page<CollegeDocument> resultado =
        commandGateway.invoke(
            FindAllCollege.class,
            FindAllCollege.Request.builder()
                .pagina(pagina)
                .paginas(paginas)
                .orderBy(orderBy)
                .direction(direction)
                .nome(nome)
                .build());

    return ResponseEntity.ok(resultado);
  }

  @Operation(summary = "Find colleges", description = "Find all colleges")
  @DocApiResponsesError
  @GetMapping("faculdades/{id}")
  public ResponseEntity<CollegeDocument> findCollegeById(@PathVariable(value = "id") Integer id) {
    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class, FindCollegeById.Request.builder().faculdadeId(id).build());

    return ResponseEntity.ok(collegeDocument);
  }

  @Operation(summary = "Atualiza faculdade", description = "Atualiza faculdade")
  @DocApiResponsesError
  @PutMapping("faculdades/{id}")
  public ResponseEntity<CollegeDocument> updateCollege(
      @PathVariable(value = "id") Integer id, @Valid @RequestBody RegisterCollegeRequest request) {
    CollegeDocument collegeDocument =
        commandGateway.invoke(
            RegisterCollege.class,
            RegisterCollege.Request.builder().name(request.getNome()).id(id).build());

    return ResponseEntity.ok(collegeDocument);
  }
  @Operation(summary = "Deleta faculdade", description = "Deleta faculdade")
  @DocApiResponsesError
  @DeleteMapping("faculdades/{id}")
  public ResponseEntity<CollegeDocument> deleteCollegeById(
          @PathVariable(value = "id") Integer id) {
    commandGateway.invoke(
                    DeleteById.class,
                    DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.CollegeDocument.class).id(id).build());
    return null;
  }
}
