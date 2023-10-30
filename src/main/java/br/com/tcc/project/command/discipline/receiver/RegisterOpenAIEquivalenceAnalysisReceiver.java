package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterOpenAIEquivalenceAnalysis;
import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.discipline.mapper.NotificationMapper;
import br.com.tcc.project.command.discipline.mapper.OpenAIEquivalenceAnalysisMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.NotificationRepository;
import br.com.tcc.project.command.repositoy.OpenAIEquivalenceAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterOpenAIEquivalenceAnalysis.class)
public class RegisterOpenAIEquivalenceAnalysisReceiver
    extends AbstractReceiver<RegisterOpenAIEquivalenceAnalysis.Request, OpenAIEquivalenceAnalysisDocument> {

  @Autowired @Setter private OpenAIEquivalenceAnalysisMapper openAIEquivalenceAnalysisMapper;

  @Autowired @Setter private OpenAIEquivalenceAnalysisRepository openAIEquivalenceAnalysisRepository;

  @Override
  protected OpenAIEquivalenceAnalysisDocument doExecute(RegisterOpenAIEquivalenceAnalysis.Request parameter) {
    return openAIEquivalenceAnalysisRepository.save(openAIEquivalenceAnalysisMapper.map(parameter));
  }
}
