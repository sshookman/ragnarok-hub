FROM ubuntu:16.04
MAINTAINER Sean Shookman <SMS112788@gmail.com>

RUN apt-get -y update
RUN apt-get -y install default-jdk
RUN apt-get -y install maven

ADD server/GOLEM.gmh /golem/server/
ADD server/Run.sh /golem/
ADD target/Golem-0.1.0.jar /golem/

EXPOSE 1127

WORKDIR /golem
CMD ["/bin/bash", "./Run.sh"]
