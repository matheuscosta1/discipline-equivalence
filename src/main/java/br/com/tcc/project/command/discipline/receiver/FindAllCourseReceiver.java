package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.FindAllCourse;
import br.com.tcc.project.command.mongo.model.CourseDocument;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@CommandReceiver(FindAllCourse.class)
public class FindAllCourseReceiver
    extends AbstractReceiver<FindAllCourse.Request, List<CourseDocument>> {

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected List<CourseDocument> doExecute(FindAllCourse.Request parameter) {

    return mongoTemplate.findAll(CourseDocument.class);
  }
}
