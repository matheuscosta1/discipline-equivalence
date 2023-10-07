package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplineRepository extends JpaRepository<DisciplineDocument, Integer> {
  List<DisciplineDocument> findByCollegeIdAndCourseId(Integer collegeId, Integer courseId);
  DisciplineDocument findByOriginCode(String originCode);
}
