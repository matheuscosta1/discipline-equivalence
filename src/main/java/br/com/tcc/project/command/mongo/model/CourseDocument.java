package br.com.tcc.project.command.mongo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Builder
@Document("cursos")
public class CourseDocument {

  private String id;

  @Field("nome")
  private String name;

  @Field("faculdade")
  private String collegeName;

  public static class FieldName {
    private FieldName() {}

    public static final String ID = "_id";
    public static final String NAME = "nome";
    public static final String COLLEGE_NAME = "faculdade";
  }
}
