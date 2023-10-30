package br.com.tcc.project.domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OpenAIEquivalenceAnalysisResponse {
  public String resemblance;
  public String difference;
  public String consideration;
}
