package br.com.tcc.project.command.repositoy;


import br.com.tcc.project.command.repositoy.model.CourseDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseDocument, Integer> {

    List<CourseDocument> findAllByCollegeId(Integer collegeId);

}
