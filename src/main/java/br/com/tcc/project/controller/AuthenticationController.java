package br.com.tcc.project.controller;

import br.com.tcc.project.command.FindUserByEmail;
import br.com.tcc.project.command.UpdateUserPassword;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.UserNotFoundException;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.controller.request.ForgotPasswordRequest;
import br.com.tcc.project.email.EmailService;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@Tag(name = "Autenticação")
@RestController
@Slf4j
@Validated
@RequestMapping(value="/auth")
public class AuthenticationController {

  @Autowired @Setter private CommandGateway commandGateway;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private EmailService emailService;

  private Random random = new Random();

  @Operation(summary = "Register new professor", description = "Register new professor")
  @DocApiResponsesError
  @PostMapping("forgot")
  public ResponseEntity<Void> registerProfessor(
      @Valid @RequestBody ForgotPasswordRequest request) {

    UsuarioDocument user = commandGateway.invoke(
            FindUserByEmail.class,
            FindUserByEmail.Request.builder().email(request.getEmail()).build());

    if(user == null) { throw new UserNotFoundException(
            DisciplineEquivalenceErrors.DEE0006.message(),
            DisciplineEquivalenceErrors.DEE0006.name(),
            DisciplineEquivalenceErrors.DEE0006.group()); }
    String newPassword = newPassword();

    user.setPassword(bCryptPasswordEncoder.encode(newPassword));

    commandGateway.invoke(
            UpdateUserPassword.class,
            UpdateUserPassword
                    .Request
                    .builder()
                    .usuarioDocument(user)
            .build()
    );

    emailService.sendNewPasswordEmail(user, newPassword);

    return ResponseEntity.noContent().build();
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
