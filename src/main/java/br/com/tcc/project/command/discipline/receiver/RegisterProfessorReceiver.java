package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.discipline.mapper.ProfessorMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterProfessor.class)
public class RegisterProfessorReceiver
    extends AbstractReceiver<RegisterProfessor.Request, ProfessorDocument> {

  @Autowired @Setter private ProfessorMapper professorMapper;

  @Autowired @Setter private ProfessorRepository professorRepository;

  @Override
  protected ProfessorDocument doExecute(RegisterProfessor.Request parameter) {
    return professorRepository.save(professorMapper.map(parameter));
  }
}
