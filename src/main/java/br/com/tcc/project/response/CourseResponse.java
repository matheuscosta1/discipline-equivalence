package br.com.tcc.project.response;

import br.com.tcc.project.command.repositoy.model.CourseDocument;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CourseResponse {
  private Integer id;
  private String nome;
  private Integer faculdadeId;

  public CourseResponse(Integer id, String nome, Integer faculdadeId) {
    this.id = id;
    this.nome = nome;
    this.faculdadeId = faculdadeId;
  }

  public CourseResponse(CourseDocument courseDocument) {}
}
