package br.com.tcc.project.command.discipline.receiver;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.FindAllCourseByCollege;
import br.com.tcc.project.command.mongo.model.CourseDocument;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@CommandReceiver(FindAllCourseByCollege.class)
public class FindAllCourseByCollegeReceiver
    extends AbstractReceiver<FindAllCourseByCollege.Request, List<CourseDocument>> {

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected List<CourseDocument> doExecute(FindAllCourseByCollege.Request parameter) {

    return mongoTemplate.find(
        query(where(CourseDocument.FieldName.COLLEGE_NAME).is(parameter.getCollegeName())),
        CourseDocument.class);
  }
}
