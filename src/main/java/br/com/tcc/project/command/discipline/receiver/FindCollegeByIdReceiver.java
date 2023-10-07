/*package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@CommandReceiver(FindCollegeById.class)
public class FindCollegeByIdReceiver
    extends AbstractReceiver<FindCollegeById.Request, CollegeDocument> {

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected CollegeDocument doExecute(FindCollegeById.Request parameter) {

    return mongoTemplate.findById(parameter.getCollegeId(), CollegeDocument.class);
  }
}
*/