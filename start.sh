#!/usr/bin/env bash
mvn clean install
java -Dkafka.servers=localhost:9092 -jar target/payara-micro.jar --deploy target/kafka-rar.rar --deploy target/ROOT.war --port 8180