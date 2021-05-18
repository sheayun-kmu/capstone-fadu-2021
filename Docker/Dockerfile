FROM ubuntu:18.04

# select the kakao apt mirror
RUN sed -i 's@archive.ubuntu.com@mirror.kakao.com@g' /etc/apt/sources.list
# Install all dependencies
RUN apt-get update && apt-get upgrade -y && \
    apt-get install -y \
        libcmocka-dev \
        gnupg gnupg2 \
        gcc g++ cmake libgmp-dev libboost-dev \
        libboost-filesystem-dev libboost-thread-dev libboost-test-dev python \
        python-pygments libsqlite3-dev libtbb-dev libz-dev libedit-dev \
        llvm-9 llvm-9-dev llvm-9-tools clang-9 wget \
        gcovr nano git curl fontconfig unzip vim sudo clang-format-10 

########## ikos ##########
MAINTAINER Maxime Arthaud <maxime.arthaud@nasa.gov>
ARG build_type=Release

# Add ppa for llvm 9.0
RUN echo "deb http://apt.llvm.org/bionic/ llvm-toolchain-bionic-9 main" >> /etc/apt/sources.list
RUN wget -O - https://apt.llvm.org/llvm-snapshot.gpg.key | apt-key add -

# Add ikos source code
RUN git clone https://github.com/NASA-SW-VnV/ikos.git ./ikos

# Build ikos
RUN rm -rf /ikos/build && mkdir /ikos/build

WORKDIR /ikos/build
RUN cmake \
        -DCMAKE_INSTALL_PREFIX="/ikos" \
        -DCMAKE_BUILD_TYPE="$build_type" \
        -DLLVM_CONFIG_EXECUTABLE="/usr/lib/llvm-9/bin/llvm-config" \
        ..
RUN make VERBOSE=1
RUN make -j$(nproc) && make install && make check

# Add ikos to the path
ENV PATH "/ikos/bin:$PATH"

########## Jenkins Dockerfile ##########

RUN sudo apt-get install openjdk-8-jdk -y
RUN sudo apt-get install maven -y

RUN wget -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
RUN sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
RUN sudo apt-get -y update && \
    sudo apt-get install -y jenkins

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
EXPOSE 8080 8081

WORKDIR /home/work
