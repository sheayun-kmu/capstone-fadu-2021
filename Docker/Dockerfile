FROM adoptopenjdk:8u282-b08-jdk-openj9-0.24.0-focal

RUN apt-get update -y
RUN apt-get upgrade -y

########## cmocka ##########
RUN apt-get install -y libcmocka-dev

########## ikos Dockerfile ##########
MAINTAINER Maxime Arthaud <maxime.arthaud@nasa.gov>
ARG njobs=2
ARG build_type=Release


# Install all dependencies
RUN apt-get install -y gnupg gnupg2
RUN apt-get install -y gcc g++ cmake libgmp-dev libboost-dev
RUN apt-get install -y libboost-filesystem-dev libboost-thread-dev libboost-test-dev python
RUN apt-get install -y python-pygments libsqlite3-dev libtbb-dev libz-dev libedit-dev
RUN apt-get install -y llvm-9 llvm-9-dev llvm-9-tools clang-9 wget
RUN apt-get install -y gcovr nano iptables net-tools

# Add ppa for llvm 9.0
RUN echo "deb http://apt.llvm.org/bionic/ llvm-toolchain-bionic-9 main" >> /etc/apt/sources.list

# Add llvm repository key
RUN wget -O - https://apt.llvm.org/llvm-snapshot.gpg.key | apt-key add -

# Refresh cache
RUN apt-get update  -y

########## Jenkins Dockerfile ##########

RUN apt-get upgrade -y \
  && apt-get install -y --no-install-recommends git curl fontconfig unzip vim sudo clang-format-10\
  && apt install -y maven \
  && curl -s https://packagecloud.io/install/repositories/github/git-lfs/script.deb.sh | bash && apt-get install -y git-lfs && git lfs install
#  && rm -rf /var/lib/apt/lists/*

#ARG user=jenkins
#ARG group=jenkins
#ARG uid=1000
#ARG gid=1000
ARG http_port=8080
ARG http_port_2=8081
ARG agent_port=50000
ARG JENKINS_HOME=/var/jenkins_home
ARG REF=/usr/share/jenkins/ref

ENV JENKINS_HOME $JENKINS_HOME
ENV JENKINS_SLAVE_AGENT_PORT ${agent_port}
ENV REF $REF

# Jenkins is run with user `jenkins`, uid = 1000
# If you bind mount a volume from the host or a data container,
# ensure you use the same uid
WORKDIR $JENKINS_HOME
#  && chown ${uid}:${gid} $JENKINS_HOME \
#  && groupadd -g ${gid} ${group} \
#  && useradd -d "$JENKINS_HOME" -u ${uid} -g ${gid} -m -s /bin/bash ${user}

# Jenkins home directory is a volume, so configuration and build history
# can be persisted and survive image upgrades
VOLUME $JENKINS_HOME

# $REF (defaults to `/usr/share/jenkins/ref/`) contains all reference configuration we want
# to set on a fresh new installation. Use it to bundle additional plugins
# or config file with your custom jenkins Docker image.
WORKDIR ${REF}/init.groovy.d

# Use tini as subreaper in Docker container to adopt zombie processes
ARG TINI_VERSION=v0.16.1
COPY tini_pub.gpg ${JENKINS_HOME}/tini_pub.gpg
RUN curl -fsSL https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini-static-$(dpkg --print-architecture) -o /sbin/tini \
  && curl -fsSL https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini-static-$(dpkg --print-architecture).asc -o /sbin/tini.asc \
  && gpg --no-tty --import ${JENKINS_HOME}/tini_pub.gpg \
  && gpg --verify /sbin/tini.asc \
  && rm -rf /sbin/tini.asc /root/.gnupg \
  && chmod +x /sbin/tini
  
# jenkins version being bundled in this docker image
ARG JENKINS_VERSION
ENV JENKINS_VERSION ${JENKINS_VERSION:-2.285}

# jenkins.war checksum, download will be validated using it
ARG JENKINS_SHA=667765e2b4d1f69a238a3da59d0ae9eb4968d041a62d61da5a45cd39ab123eab

# Can be used to customize where jenkins.war get downloaded from
ARG JENKINS_URL=https://repo.jenkins-ci.org/public/org/jenkins-ci/main/jenkins-war/${JENKINS_VERSION}/jenkins-war-${JENKINS_VERSION}.war

# could use ADD but this one does not check Last-Modified header neither does it allow to control checksum
# see https://github.com/docker/docker/issues/8331
RUN curl -fsSL ${JENKINS_URL} -o /usr/share/jenkins/jenkins.war \
  && echo "${JENKINS_SHA}  /usr/share/jenkins/jenkins.war" | sha256sum -c -

ENV JENKINS_UC https://updates.jenkins.io
ENV JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental
ENV JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals
#RUN chown -R ${user} "$JENKINS_HOME" "$REF"

ARG PLUGIN_CLI_URL=https://github.com/jenkinsci/plugin-installation-manager-tool/releases/download/2.8.0/jenkins-plugin-manager-2.8.0.jar
RUN curl -fsSL ${PLUGIN_CLI_URL} -o /usr/lib/jenkins-plugin-manager.jar

# for main web interface:
EXPOSE ${http_port}
EXPOSE ${http_port_2}

# will be used by attached slave agents:
EXPOSE ${agent_port}

ENV COPY_REFERENCE_FILE_LOG $JENKINS_HOME/copy_reference_file.log

#USER ${user}

COPY jenkins-support /usr/local/bin/jenkins-support
COPY jenkins.sh /usr/local/bin/jenkins.sh
COPY tini-shim.sh /bin/tini
COPY jenkins-plugin-cli.sh /bin/jenkins-plugin-cli
#COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /usr/local/bin/jenkins.sh
#RUN chmod +x /entrypoint.sh

# set variables to create shared class cache
ENV JAVA_SCC_OPTS "-Xshareclasses:name=jenkins_scc,cacheDir=/tmp,enableBCI -Xscmx80M"
ENV JAVA_OLD_OPTS ${JAVA_OPTS}
ENV JAVA_OPTS "${JAVA_SCC_OPTS} ${JAVA_OPTS}"

# create shared class cache
RUN /sbin/tini -- /usr/local/bin/jenkins.sh > /dev/null & sleep 20

# revert JAVA_OPTS to not have shared class cache
ENV JAVA_SCC_OPTS "-Xshareclasses:name=jenkins_scc,cacheDir=/tmp,readonly"
ENV JAVA_OPTS "${JAVA_SCC_OPTS} ${JAVA_OLD_OPTS}"

WORKDIR /home/work

ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]

# from a derived Dockerfile, can use `RUN install-plugins.sh active.txt` to setup ${REF}/plugins from a support bundle
COPY install-plugins.sh /usr/local/bin/install-plugins.sh