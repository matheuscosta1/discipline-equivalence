package br.com.tcc.project.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CourseResponse {
  private Integer id;
  private String nome;
  private Integer faculdadeId;
}
