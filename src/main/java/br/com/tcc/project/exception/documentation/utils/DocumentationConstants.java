package br.com.tcc.project.exception.documentation.utils;

public class DocumentationConstants {

  private DocumentationConstants() {
    throw new IllegalArgumentException("Utility class");
  }

  public static final String BAD_REQUEST_RESPONSE_CODE = "400";
  public static final String NOT_FOUND_RESPONSE_CODE = "404";
  public static final String UNPROCESSABLE_ENTITY_RESPONSE_CODE = "422";
  public static final String INTERNAL_ERROR_RESPONSE_CODE = "500";

  public static final String INVALID_REQUEST = "Requisição Inválida.";
  public static final String INVALID_REQUEST_DESCRIPTION = "A requisição contém sintaxe inválida.";
  public static final String UNPROCESSABLE_ENTITY_RESPONSE = "Erro ao processar requisição.";
  public static final String NOT_FOUND = "Not found";
  public static final String UNPROCESSABLE_ENTITY_DESCRIPTION =
      "O cliente não deve repetir esta requisição sem modificações.";
  public static final String INTERNAL_ERROR = "Erro interno.";
  public static final String INTERNAL_ERROR_DESCRIPTION = "Ocorreu um erro interno no servidor.";

  public static final String NOT_FOUND_EXAMPLE = "{\"status\": 404,\"error\": \"Not found.\"}";
  public static final String INVALID_REQUEST_EXAMPLE =
      "{\n"
          + "  \"codigo\": \"PLA0002\",\n"
          + "  \"grupo\": 1000,\n"
          + "  \"mensagem\": \"Dados inválidos\",\n"
          + "  \"erros\": [\n"
          + "    {\n"
          + "      \"campo\": \"codigo\",\n"
          + "      \"erro\": \"não deve estar em branco.\"\n"
          + "    },\n"
          + "    {\n"
          + "      \"campo\": \"valor\",\n"
          + "      \"erro\": \"deve ser maior que zero.\"\n"
          + "    }\n"
          + "  ]\n"
          + "}";
  public static final String UNPROCESSABLE_ENTITY_EXAMPLE =
      "{\n"
          + "  \"codigo\": \"PLA0005\",\n"
          + "  \"grupo\": 1000,\n"
          + "  \"mensagem\": \"Falha ao processar requisição.\"\n"
          + "}";
  public static final String INTERNAL_ERROR_EXAMPLE =
      "{\n" + "  \"grupo\": 0,\n" + "  \"mensagem\": \"Ocorreu um problema interno.\"\n" + "}";
}
