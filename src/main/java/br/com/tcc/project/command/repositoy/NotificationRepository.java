package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<NotificationDocument, Integer> { }
