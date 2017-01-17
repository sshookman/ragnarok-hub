FROM sshookman/base-java:latest
MAINTAINER Sean Shookman <SMS112788@gmail.com>

EXPOSE 1127

WORKDIR /app
ADD . /app

RUN ["./gradlew", "clean", "test", "build"]
CMD ["nohup", "java", "-jar", "build/libs/enchiridion-0.0.1-SNAPSHOT.jar"]