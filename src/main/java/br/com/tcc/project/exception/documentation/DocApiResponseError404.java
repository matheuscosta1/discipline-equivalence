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
    responseCode = DocumentationConstants.NOT_FOUND_RESPONSE_CODE,
    description = DocumentationConstants.NOT_FOUND,
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = {
              @ExampleObject(
                  name = DocumentationConstants.NOT_FOUND,
                  summary = DocumentationConstants.NOT_FOUND,
                  value = DocumentationConstants.NOT_FOUND_EXAMPLE)
            }))
public @interface DocApiResponseError404 {}
