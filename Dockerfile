FROM openjdk:11-jdk
#作者名字（可以自定义
MAINTAINER lenyuqin
WORKDIR /
ARG JAR_FILE
COPY ${JAR_FILE} b-data.jar
#暴露端口，要修改
EXPOSE 8383
ENTRYPOINT ["java","-jar","/b-data.jar"]