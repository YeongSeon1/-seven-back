FROM openjdk:11.0.13-jre

COPY ./build/libs/module3-0.0.1-SNAPSHOT.jar /eat-back.jar

CMD java -jar /eat-back.jar

