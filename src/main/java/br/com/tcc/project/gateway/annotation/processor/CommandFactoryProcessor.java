package br.com.tcc.project.gateway.annotation.processor;

import br.com.tcc.project.command.interfaces.Command;
import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes(
    "br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class CommandFactoryProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Configuration cfg = configureFreemarker();

    for (TypeElement annotation : annotations) {
      Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
      List<String> commandClasses =
          annotatedElements.stream()
              .map(TypeElement.class::cast)
              .map(TypeElement::getQualifiedName)
              .map(Objects::toString)
              .collect(Collectors.toList());
      commandClasses.forEach(
          className -> {
            int lastDot = className.lastIndexOf('.');
            String packageName = className.substring(0, lastDot);
            String commandClassName = className.substring(lastDot + 1);
            String factoryClassName = className + "CommandFactory";

            try {
              JavaFileObject factoryFile =
                  processingEnv.getFiler().createSourceFile(factoryClassName);
              try (Writer out = factoryFile.openWriter()) {
                Template template = cfg.getTemplate("templates/CommandFactory.ftl");
                template.process(
                    Map.of(
                        "packageName",
                        packageName,
                        "commandClassName",
                        commandClassName,
                        "factoryClassName",
                        factoryClassName),
                    out);
              }
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          });
    }

    return true;
  }

  protected Configuration configureFreemarker() {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
    cfg.setClassForTemplateLoading(Command.class, "/");
    return cfg;
  }
}
