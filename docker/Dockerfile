FROM amazoncorretto:11 as bulid

WORKDIR application
ENV TZ America/Sao_Paulo
ADD application.jar /app/
RUN java -Djarmode=layertools -jar /app/application.jar extract

FROM amazoncorretto:11
WORKDIR application
COPY --from=bulid application/dependencies/ ./
COPY --from=bulid application/spring-boot-loader/ ./
COPY --from=bulid application/snapshot-dependencies/ ./
COPY --from=bulid application/application/ ./
ENTRYPOINT exec java $JAVA_OPTIONS org.springframework.boot.loader.JarLauncher

EXPOSE 8080