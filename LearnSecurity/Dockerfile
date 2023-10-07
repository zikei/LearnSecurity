FROM eclipse-temurin:17-jdk-alpine
RUN mkdir /opt/xls
WORKDIR /opt/xls
COPY LearnSecurity/build/libs/LearnSecurity-0.0.1-SNAPSHOT.jar /opt/xls/xls.jar
ENTRYPOINT ["java","-jar","/opt/xls/xls.jar"]
# 実習コンテンツフォルダのコピー
COPY practice/ /opt/xls/practice