package br.com.tcc.project.response;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class FaculdadeResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String nome;

  public FaculdadeResponse() {
  }

  public FaculdadeResponse(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }
}
