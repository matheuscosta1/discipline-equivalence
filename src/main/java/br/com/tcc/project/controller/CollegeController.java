package br.com.tcc.project.controller;

import br.com.tcc.project.command.FindAllCollege;
import br.com.tcc.project.command.RegisterCollege;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.controller.mapper.RegisterDisciplineControllerMapper;
import br.com.tcc.project.controller.request.RegisterCollegeRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
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

@Tag(name = "College")
@RestController
@Slf4j
@Validated
public class CollegeController {

  @Autowired @Setter private RegisterDisciplineControllerMapper registerDisciplineMapper;

  @Autowired @Setter private CommandGateway commandGateway;

  @Operation(summary = "Register new college", description = "Register new college")
  @DocApiResponsesError
  @PostMapping("register-college")
  public ResponseEntity<Void> registerCollege(@Valid @RequestBody RegisterCollegeRequest request) {

    commandGateway.invoke(
        RegisterCollege.class, RegisterCollege.Request.builder().name(request.getName()).build());

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Find colleges", description = "Find all colleges")
  @DocApiResponsesError
  @GetMapping("find-colleges")
  public ResponseEntity<List<CollegeDocument>> findAllColleges() {

    List<CollegeDocument> colleges =
        commandGateway.invoke(
            FindAllCollege.class, FindAllCollege.Request.builder().nothing("nothing").build());

    return ResponseEntity.ok(colleges);
  }
}
