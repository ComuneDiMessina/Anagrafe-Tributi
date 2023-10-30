FROM openjdk:11-slim

RUN mkdir -p /app/config

RUN addgroup --system spring &&  APPGROUP=`grep "spring" /etc/group|cut -d: -f3` && adduser --system --gid $APPGROUP spring
RUN chown -R spring:spring /app

USER spring

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} /app/app.jar

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]