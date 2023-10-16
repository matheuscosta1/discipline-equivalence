package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UsuarioDocument, Integer> {

    @Transactional(readOnly=true)
    UsuarioDocument findByEmail(String email);
}
