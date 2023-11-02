package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorDocument, Integer> {

  Page<ProfessorDocument> findByDisciplinaIdAndStatus(Integer disciplinaId, String status, Pageable pageRequest);

  Page<ProfessorDocument> findByNomeContainingAndStatus(String nome, String status, Pageable pageRequest);

  ProfessorDocument findByUsuarioIdAndStatus(Integer usuarioId, String status);
  Page<ProfessorDocument> findAllByStatus(String status, Pageable pageRequest);

}
