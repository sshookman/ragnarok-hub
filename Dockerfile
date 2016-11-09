FROM ubuntu:16.04
MAINTAINER Sean Shookman <SMS112788@gmail.com>

RUN apt-get -y update
RUN apt-get -y install default-jdk

#ADD server/GOLEM.gmh /app/server/
#ADD server/TEST_GAME.glm /app/library/
#ADD target/sqlite-jdbc-3.8.11.2.jar /app/
ADD build/libs/enchiridion-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

EXPOSE 1127

CMD ["nohup", "java", "-jar", "enchiridion-0.0.1-SNAPSHOT.jar"]