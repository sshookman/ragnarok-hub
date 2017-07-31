FROM seanshookman/ubuntu-jdk-gradle:latest
MAINTAINER Sean Shookman <SMS112788@gmail.com>

EXPOSE 1127

WORKDIR /app
ADD . /app

RUN ["gradle", "clean", "test", "build"]
CMD ["nohup", "gradle", "bootRun"]