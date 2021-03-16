FROM ubuntu:18.04

RUN apt-get update \
  && apt-get upgrade -y

RUN apt-get install sudo
RUN apt-get install -y git curl
RUN apt-get install -y vim
RUN apt-get install -y wget

# JDK 8 설치
RUN apt install -y openjdk-8-jdk

# TODO : Jenkins 설치하기

# Jenkins repository key 다운로드 및 설치 + maven
RUN apt-get install -y gnupg2
#RUN wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
#RUN sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
RUN sudo apt install -y maven
#
ARG http_port=8080
ARG agent_port=50000

ENV JENKINS_HOME /var/jenkins_home
ENV JENKINS_SLAVE_AGENT_PORT ${agent_port}

# jenkins version being bundled in this docker image
ARG JENKINS_VERSION
ENV JENKINS_VERSION ${JENKINS_VERSION:-2.60.3}

# jenkins.war checksum, download will be validated using it
ARG JENKINS_SHA=2d71b8f87c8417f9303a73d52901a59678ee6c0eefcf7325efed6035ff39372a

# Can be used to customize where jenkins.war get downloaded from
ARG JENKINS_URL=https://repo.jenkins-ci.org/public/org/jenkins-ci/main/jenkins-war/${JENKINS_VERSION}/jenkins-war-${JENKINS_VERSION}.war

# could use ADD but this one does not check Last-Modified header neither does it allow to control checksum
# see https://github.com/docker/docker/issues/8331
RUN curl -fsSL ${JENKINS_URL} -o /usr/share/jenkins/jenkins.war \
  && echo "${JENKINS_SHA}  /usr/share/jenkins/jenkins.war" | sha256sum -c -
COPY jenkins.sh /usr/local/bin/jenkins.sh

RUN apt-get update \
  && apt-get install -y --no-install-recommends git curl gnupg fontconfig unzip nano \
  && curl -s https://packagecloud.io/install/repositories/github/git-lfs/script.deb.sh | bash && apt-get install -y git-lfs && git lfs install \
  && rm -rf /var/lib/apt/lists/*

ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080

#ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]

