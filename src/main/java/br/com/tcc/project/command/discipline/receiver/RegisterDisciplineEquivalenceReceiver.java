/*package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.RegisterDisciplineEquivalence;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterDisciplineEquivalence.class)
public class RegisterDisciplineEquivalenceReceiver
    extends AbstractReceiver<RegisterDisciplineEquivalence.Request, Void> {

  @Autowired @Setter private RegisterDisciplineMapper registerDisciplineMapper;

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected Void doExecute(RegisterDisciplineEquivalence.Request parameter) {
    mongoTemplate.save(registerDisciplineMapper.map(parameter));
    return null;
  }
}*/
