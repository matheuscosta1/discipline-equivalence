package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterCollege;
import br.com.tcc.project.command.discipline.mapper.RegisterCollegeMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterCollege.class)
public class RegisterCollegeReceiver extends AbstractReceiver<RegisterCollege.Request, CollegeDocument> {

  @Autowired @Setter private RegisterCollegeMapper registerCollegeMapper;

  @Autowired @Setter private CollegeRepository collegeRepository;

  @Override
  protected CollegeDocument doExecute(RegisterCollege.Request parameter) {
    CollegeDocument save = collegeRepository.save(registerCollegeMapper.map(parameter));
    return save;
  }
}
