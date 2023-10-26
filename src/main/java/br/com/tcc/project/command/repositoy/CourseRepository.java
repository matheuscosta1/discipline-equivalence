package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.CourseDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseDocument, Integer> {

  // List<CourseDocument> findAllByCollegeId(Integer collegeId);

  Page<CourseDocument> findByNomeContaining(String nome, Pageable pageRequest);

  Page<CourseDocument> findByFaculdadeId(Integer faculdadeId, Pageable pageRequest);
  CourseDocument findByNomeAndFaculdadeId(String nome, Integer faculdadeId);
}
