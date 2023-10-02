package br.com.tcc.project.exception.documentation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DocApiResponseError400
@DocApiResponseError422
@DocApiResponseError500
public @interface DocApiResponsesError {}
