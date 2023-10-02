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
    responseCode = DocumentationConstants.BAD_REQUEST_RESPONSE_CODE,
    description = DocumentationConstants.INVALID_REQUEST,
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = {
              @ExampleObject(
                  name = DocumentationConstants.INVALID_REQUEST,
                  summary = DocumentationConstants.INVALID_REQUEST,
                  description = DocumentationConstants.INVALID_REQUEST_DESCRIPTION,
                  value = DocumentationConstants.INVALID_REQUEST_EXAMPLE)
            }))
public @interface DocApiResponseError400 {}
