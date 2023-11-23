FROM eclipse-temurin:17-jdk-alpine
RUN mkdir /opt/xlsM
WORKDIR /opt/xlsM
COPY LearnSecurityManager/build/libs/LearnSecurityManager-0.0.1-SNAPSHOT.jar /opt/xlsM/xlsM.jar
ENTRYPOINT ["java","-jar","/opt/xlsM/xlsM.jar"]
