package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<DisciplineDocument, Integer> {
  Page<DisciplineDocument> findByFaculdadeIdAndCursoId(
      Integer collegeId, Integer courseId, Pageable pageRequest);

  DisciplineDocument findByCodigoOrigem(String codigoOrigem);

  Page<DisciplineDocument> findByNomeContaining(String nome, Pageable pageRequest);

  DisciplineDocument findByCodigoOrigemAndFaculdadeIdAndCursoId(
          String codigoOrigem, Integer collegeId, Integer courseId);
}
