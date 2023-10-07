package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterCourseMapper {

  CourseDocument map(RegisterCourse.Request source);
}
