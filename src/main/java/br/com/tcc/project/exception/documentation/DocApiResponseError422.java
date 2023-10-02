package br.com.tcc.project.exception.documentation;

import br.com.tcc.project.exception.documentation.utils.DocumentationConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.*;
import org.springframework.http.MediaType;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponse(
    responseCode = DocumentationConstants.UNPROCESSABLE_ENTITY_RESPONSE_CODE,
    description = DocumentationConstants.UNPROCESSABLE_ENTITY_RESPONSE,
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = {
              @ExampleObject(
                  name = DocumentationConstants.UNPROCESSABLE_ENTITY_RESPONSE,
                  summary = DocumentationConstants.UNPROCESSABLE_ENTITY_RESPONSE,
                  description = DocumentationConstants.UNPROCESSABLE_ENTITY_DESCRIPTION,
                  value = DocumentationConstants.UNPROCESSABLE_ENTITY_EXAMPLE)
            }))
public @interface DocApiResponseError422 {}
