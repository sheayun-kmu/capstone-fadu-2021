FROM ubuntu:18.04

# Install all dependencies
RUN apt-get update && apt-get upgrade -y && \
    apt-get install -y \
        libcmocka-dev \
        gnupg gnupg2 \
        gcc g++ cmake libgmp-dev libboost-dev \
        libboost-filesystem-dev libboost-thread-dev libboost-test-dev python \
        python-pygments libsqlite3-dev libtbb-dev libz-dev libedit-dev \
        llvm-9 llvm-9-dev llvm-9-tools clang-9 wget \
        gcovr nano iptables net-tools \ 
        git curl fontconfig unzip vim sudo clang-format-10

########## ikos ##########
# TODO: ikos 환경 설치하기
MAINTAINER Maxime Arthaud <maxime.arthaud@nasa.gov>
ARG njobs=2
ARG build_type=Release

# Add ppa for llvm 9.0
RUN echo "deb http://apt.llvm.org/bionic/ llvm-toolchain-bionic-9 main" >> /etc/apt/sources.list
RUN wget -O - https://apt.llvm.org/llvm-snapshot.gpg.key | apt-key add -

# Add ikos source code
RUN git clone https://github.com/NASA-SW-VnV/ikos.git ./ikos

# Build ikos
RUN rm -rf /ikos/build && mkdir /ikos/build

WORKDIR /ikos/build
ENV MAKEFLAGS "-j$njobs"
RUN cmake \
        -DCMAKE_INSTALL_PREFIX="/ikos" \
        -DCMAKE_BUILD_TYPE="$build_type" \
        -DLLVM_CONFIG_EXECUTABLE="/usr/lib/llvm-9/bin/llvm-config" \
        ..
RUN make && make install && make check

# Add ikos to the path
ENV PATH "/ikos/bin:$PATH"

########## Jenkins Dockerfile ##########

RUN apt-get install sudo -y && \
    sudo apt-get install -y \
        openjdk-8-jdk \
        maven

RUN apt-get install -y wget gnupg gnupg2
RUN wget -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
RUN sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
RUN sudo apt-get install -y jenkins && \
    rm -rf /var/lib/apt/lists/* 

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
EXPOSE 8080 8081

WORKDIR /home/work
