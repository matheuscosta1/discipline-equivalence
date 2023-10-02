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
    responseCode = DocumentationConstants.INTERNAL_ERROR_RESPONSE_CODE,
    description = DocumentationConstants.INTERNAL_ERROR,
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = {
              @ExampleObject(
                  name = DocumentationConstants.INTERNAL_ERROR,
                  summary = DocumentationConstants.INTERNAL_ERROR,
                  description = DocumentationConstants.INTERNAL_ERROR_DESCRIPTION,
                  value = DocumentationConstants.INTERNAL_ERROR_EXAMPLE)
            }))
public @interface DocApiResponseError500 {}
