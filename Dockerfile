FROM ubuntu:16.04
MAINTAINER Sean Shookman <SMS112788@gmail.com>

RUN apt-get -y update
ADD GOLEM.gmh /
ADD library/ /
