/*package br.com.tcc.project.command.discipline.receiver;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.FindAllDisciplineByCollegeAndCourse;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@CommandReceiver(FindAllDisciplineByCollegeAndCourse.class)
public class FindAllDisciplineByCollegeAndCourseReceiver
    extends AbstractReceiver<
        FindAllDisciplineByCollegeAndCourse.Request, List<DisciplineDocument>> {

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected List<DisciplineDocument> doExecute(
      FindAllDisciplineByCollegeAndCourse.Request parameter) {
    return mongoTemplate.find(
        query(
            where(DisciplineDocument.FieldName.COLLEGE)
                .is(parameter.getCollegeName())
                .and(DisciplineDocument.FieldName.COURSE)
                .is(parameter.getCourseName())),
        DisciplineDocument.class);
  }
}
*/