package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationDocument, Integer> {


    @Query(value = "SELECT * FROM notificacao WHERE data_maxima = CAST(:dataMaxima AS DATE) AND status <> :status AND equivalencia_id IS NULL", nativeQuery = true)
    List<NotificationDocument> findByDataMaximaAndStatusIsNotSent(@Param("dataMaxima") String dataMaxima, @Param("status") String status);
    @Query(value = "SELECT * FROM notificacao WHERE analise_id = :analiseId AND status = :status", nativeQuery = true)
    NotificationDocument findByAnaliseIdAndStatusPending(@Param("analiseId") Integer analiseId, @Param("status") String status);

    @Query(value = "SELECT * FROM notificacao WHERE analise_id = :analiseId AND equivalencia_id IS NULL", nativeQuery = true)
    NotificationDocument findByAnaliseId(@Param("analiseId") Integer analiseId);

    @Query(value = "SELECT * FROM notificacao WHERE status <> :status AND equivalencia_id IS NOT NULL", nativeQuery = true)
    List<NotificationDocument> findAllEquivalenceNotifications(@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM notificacao WHERE analise_id = :analiseId", nativeQuery = true)
    void deleteByAnaliseId(@Param("analiseId") Integer analiseId);
}
