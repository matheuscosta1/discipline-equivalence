package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorDocument, Integer> {

  Page<ProfessorDocument> findByDisciplinaId(Integer disciplinaId, Pageable pageRequest);

  Page<ProfessorDocument> findByNomeContaining(String nome, Pageable pageRequest);

  ProfessorDocument findByUsuarioId(Integer usuarioId);

}
