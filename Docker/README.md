
## Build
~~~shell
docker build -t {TAG} -f {Dockefile} {WORKDIR}
~~~
If the dockerfile name is a **Dockerfile**, the -f option is omitted.
#### example
```shell
docker build -t jenkins-plugin-delevop .
```

## Usage
~~~shell
docker run --cap-add=NET_ADMIN -p 8080:8080 -p 8081:8081 -p 50000:50000 {IMAGE:TAG}
~~~

## Connect

~~~shell
docker exec -it {CONTAINER_ID} /bin/bash
~~~


