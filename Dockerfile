FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/springboot-conf-gateway-api-oauth2-0.0.1-SNAPSHOT.jar springboot-conf-gateway-api-oauth2.jar
COPY target/springboot-conf-gateway-api-oauth2-0.0.1-SNAPSHOT.jar springboot-conf-gateway-api-oauth2-0.0.1-SNAPSHOT.jar
EXPOSE 7766
ENTRYPOINT ["java", "-jar", "springboot-conf-gateway-api-oauth2-0.0.1-SNAPSHOT.jar"]