
#Location and commands to run

FROM adoptopenjdk:16_36-jre-hotspot as builder
WORKDIR extracted
ADD ./target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk:16_36-jre-hotspot
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

#Port number for spring-boot application.yml
EXPOSE 9001

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]