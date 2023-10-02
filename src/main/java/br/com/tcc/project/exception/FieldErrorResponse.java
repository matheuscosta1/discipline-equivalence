package br.com.tcc.project.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("campo")
  private String field;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("erro")
  private String error;
}
