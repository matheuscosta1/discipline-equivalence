package br.com.tcc.project.command.repositoy.model;

import br.com.tcc.project.domain.NotificationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "notificacao")
public class NotificationDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "analiseId")
  private AnalisesDocument analisesDocument;

  private String status;

  private String email;

  public Date dataMaxima;

  public NotificationDocument() {}

  public NotificationDocument(Integer id, AnalisesDocument analisesDocument, String status, String email, Date dataMaxima) {
    this.id = id;
    this.analisesDocument = analisesDocument;
    this.status = status;
    this.email = email;
    this.dataMaxima = dataMaxima;
  }
}
