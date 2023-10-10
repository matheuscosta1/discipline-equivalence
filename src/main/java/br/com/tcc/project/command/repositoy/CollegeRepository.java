package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<CollegeDocument, Integer> {

  CollegeDocument findByNomeContaining(String name);

  Page<CollegeDocument> findByNomeContaining(String nome, Pageable pageRequest);

}
