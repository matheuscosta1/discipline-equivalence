package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationDocument, Integer> {


    @Query(value = "SELECT * FROM notificacao WHERE data_maxima = :dataMaxima and status = :status", nativeQuery = true)
    List<NotificationDocument> findByDataMaximaAndStatusPending(@Param("dataMaxima") String dataMaxima, @Param("status") String status);
}
