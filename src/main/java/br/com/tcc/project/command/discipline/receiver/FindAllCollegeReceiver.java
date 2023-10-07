package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllCollege;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindAllCollege.class)
public class FindAllCollegeReceiver
    extends AbstractReceiver<FindAllCollege.Request, List<CollegeDocument>> {

  @Autowired @Setter private CollegeRepository collegeRepository;

  @Override
  protected List<CollegeDocument> doExecute(FindAllCollege.Request parameter) {
    return collegeRepository.findAll();
  }
}
