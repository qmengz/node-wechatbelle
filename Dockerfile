
FROM hub.fenxibao.com/base/java:oracle-java-8-jdk

WORKDIR /node-wechatbelle
COPY target /node-wechatbelle/target

EXPOSE 8080
CMD ["java", "-jar", "target/node-wechatbelle.jar", "server", "application.yml"]