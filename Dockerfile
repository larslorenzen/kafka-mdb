FROM java:8-jdk

RUN mkdir app
WORKDIR "/app"

RUN curl -OLs http://central.maven.org/maven2/fish/payara/extras/payara-micro/4.1.2.174/payara-micro-4.1.2.174.jar
RUN curl -OLs http://central.maven.org/maven2/fish/payara/cloud/connectors/kafka/kafka-rar/0.2.0/kafka-rar-0.2.0.rar

COPY target/kafka-mdb.war .

CMD exec java -jar /app/payara-micro-4.1.2.174.jar --deploy kafka-rar-0.2.0.rar --deploy kafka-mdb.war
