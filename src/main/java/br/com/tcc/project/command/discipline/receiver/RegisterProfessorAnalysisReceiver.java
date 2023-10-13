package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterProfessorAnalysis;
import br.com.tcc.project.command.discipline.mapper.ProfessorAnalysisMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterProfessorAnalysis.class)
public class RegisterProfessorAnalysisReceiver
    extends AbstractReceiver<RegisterProfessorAnalysis.Request, AnalisesDocument> {

  @Autowired @Setter private ProfessorAnalysisMapper professorMapper;

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;

  @Override
  protected AnalisesDocument doExecute(RegisterProfessorAnalysis.Request parameter) {
    return professorAnalysisRepository.save(professorMapper.map(parameter));
  }
}
