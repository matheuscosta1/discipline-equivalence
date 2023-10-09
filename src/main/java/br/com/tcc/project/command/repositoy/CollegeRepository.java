package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CollegeRepository extends JpaRepository<CollegeDocument, Integer> {

  CollegeDocument findByNome(String name);

  Page<CollegeDocument> findByNome(String nome, Pageable pageRequest);

}
