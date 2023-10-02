package br.com.tcc.project.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("codigo")
  protected String code;

  @JsonProperty("mensagem")
  private String message;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("titulo")
  private String title;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("erros")
  @SuppressWarnings("squid:S1948")
  private List<FieldErrorResponse> errors;

  public void addError(final String field, final String error) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(FieldErrorResponse.builder().field(field).error(error).build());
  }

  public void addError(final String error) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(FieldErrorResponse.builder().error(error).build());
  }
}
