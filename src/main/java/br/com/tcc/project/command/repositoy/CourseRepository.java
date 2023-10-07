package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.CourseDocument;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseDocument, Integer> {

  List<CourseDocument> findAllByCollegeId(Integer collegeId);
}
