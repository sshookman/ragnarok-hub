FROM ubuntu:16.04
MAINTAINER Sean Shookman <SMS112788@gmail.com>

RUN apt-get -y update
RUN apt-get -y install default-jdk

EXPOSE 1127

WORKDIR /app
ADD . /app

RUN ["./gradlew", "clean", "test", "build"]
CMD ["nohup", "java", "-jar", "build/libs/enchiridion-0.0.1-SNAPSHOT.jar"]