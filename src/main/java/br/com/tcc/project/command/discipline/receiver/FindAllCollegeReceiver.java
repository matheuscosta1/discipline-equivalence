package br.com.tcc.project.command.discipline.receiver;


import br.com.tcc.project.command.FindAllCollege;
import br.com.tcc.project.command.mongo.model.CollegeDocument;
import java.util.List;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@CommandReceiver(FindAllCollege.class)
public class FindAllCollegeReceiver
    extends AbstractReceiver<FindAllCollege.Request, List<CollegeDocument>> {

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected List<CollegeDocument> doExecute(FindAllCollege.Request parameter) {

    return mongoTemplate.findAll(CollegeDocument.class);
  }
}
