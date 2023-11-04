package br.com.tcc.project.controller;

import br.com.tcc.project.command.FindProfessorByUserId;
import br.com.tcc.project.command.FindUserByEmail;
import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.UpdateUserPassword;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.UserException;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.controller.request.ChangeUserInformationRequest;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@Tag(name = "Autenticação")
@RestController
@Slf4j
@Validated
@RequestMapping(value="/usuario")
public class UserController {

  @Autowired @Setter private CommandGateway commandGateway;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  private final Random random = new Random();

  @Operation(summary = "Register new professor", description = "Register new professor")
  @DocApiResponsesError
  @GetMapping("dados/{email}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
  public ResponseEntity<UserResponse> getUserInformation(@PathVariable(value = "email") String email) {

    UsuarioDocument user = commandGateway.invoke(
            FindUserByEmail.class,
            FindUserByEmail.Request.builder().email(email).build());

    if(user == null) {
      throw new UserException(
            DisciplineEquivalenceErrors.DEE0006.message(),
            DisciplineEquivalenceErrors.DEE0006.name(),
            DisciplineEquivalenceErrors.DEE0006.group());
    }

    return ResponseEntity.ok(UserResponse.builder().id(user.getId()).nome(user.getNome()).email(user.getEmail()).build());
  }

  @Operation(summary = "Register new professor", description = "Register new professor")
  @DocApiResponsesError
  @PutMapping("atualizar-dados")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
  public ResponseEntity<Void> changeUserInformation(@Valid @RequestBody ChangeUserInformationRequest request) {
    UsuarioDocument user;
    user = commandGateway.invoke(
            FindUserByEmail.class,
            FindUserByEmail.Request.builder().email(request.getEmail()).build());

    if(user == null) {
      log.error(DisciplineEquivalenceErrors.DEE0006.message());
      throw new UserException(
            DisciplineEquivalenceErrors.DEE0006.message(),
            DisciplineEquivalenceErrors.DEE0006.name(),
            DisciplineEquivalenceErrors.DEE0006.group());
    }

    if(!bCryptPasswordEncoder.matches(request.getSenhaAtual(), user.getPassword())) {
      throw new UserException(
            DisciplineEquivalenceErrors.DEE0014.message(),
            DisciplineEquivalenceErrors.DEE0014.name(),
            DisciplineEquivalenceErrors.DEE0014.group());
    }

    if(!(request.getNovoEmail().equals(user.getEmail())) && commandGateway.invoke(
            FindUserByEmail.class,
            FindUserByEmail.Request.builder().email(request.getNovoEmail()).build()) != null) {
      log.error(DisciplineEquivalenceErrors.DEE0015.message());
      throw new UserException(
            DisciplineEquivalenceErrors.DEE0015.message(),
            DisciplineEquivalenceErrors.DEE0015.name(),
            DisciplineEquivalenceErrors.DEE0015.group());
    }

    user.setPassword(bCryptPasswordEncoder.encode(request.getNovaSenha()));
    user.setNome(request.getNome());
    user.setEmail(request.getNovoEmail());

    if(isUserProfessor(user)) {
      ProfessorDocument professorDocument = commandGateway.invoke(
              FindProfessorByUserId.class,
              FindProfessorByUserId.Request.builder().id(user.getId()).build()
      );

      commandGateway.invoke(
              RegisterProfessor.class,
              RegisterProfessor.Request.builder()
                      .id(professorDocument.getId())
                      .nome(request.getNome())
                      .collegeDocument(professorDocument.getFaculdade())
                      .courseDocument(professorDocument.getCurso())
                      .disciplineDocument(professorDocument.getDisciplina())
                      .usuarioDocument(professorDocument.getUsuario())
                      .status(professorDocument.getStatus()).build()
      );
    }

    commandGateway.invoke(
            UpdateUserPassword.class,
            UpdateUserPassword
                    .Request
                    .builder()
                    .usuarioDocument(user)
                    .build()
    );

    return ResponseEntity.noContent().build();
  }

  private static boolean isUserProfessor(UsuarioDocument user) {
    return user.getPerfil().equals(2);
  }
}
