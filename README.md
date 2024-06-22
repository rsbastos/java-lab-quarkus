# Java-Lab-Quarkus
Java Quarkus project for DIO Bootcamp - Java AI Powered

# Election System with Java and Quarkus


Distributed and scalable system for elections, 
containing applications for voting, election management  
and querying results, using:

  - Java 17

  - Quarkus

  - JUnit

  - Testcontainers

  - Hibernate

  - Flyway

  - Docker

  - Traefik

  - MariaDB

  - Redis

  - Graylog

  - OpenSearch

  - MongoDB

  - Jaeger Tracing


###  Docker Compose
To start the applications, you must upload the containers to set up the infrastructure, typing the following commands at the program root:

```bash
docker compose up -d reverse-proxy
docker compose up -d jaeger
docker compose up -d mongodb opensearch
docker compose up -d graylog
```

After graylog goes up, to configure it for UDP protocol, you can use the command below, only once:
```bash
curl -H "Content-Type: application/json" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "X-Requested-By: curl" -X POST -v -d '{"title":"udp input","configuration":{"recv_buffer_size":262144,"bind_address":"0.0.0.0","port":12201,"decompress_size_limit":8388608},"type":"org.graylog2.inputs.gelf.udp.GELFUDPInput","global":true}' http://logging.private.dio.localhost/api/system/inputs
```


##  CI/CD Build

For developed applications, first create the build for each application, below there are the commands to build the three applications:

```bash
./cicd-build.sh election-management
./cicd-build.sh voting-app
./cicd-build.sh result-app
```

###  CI/CD Blue Green Deployment
With the command for CI/CD Build, the script already creates the docker image with the version as TAG. To perform a blue green deployment, use the commands below depending on the application and review the version (TAG) that comes after the application name:

```bash
./cicd-blue-green-deployment.sh election-management 1.0.0
./cicd-blue-green-deployment.sh voting-app 1.0.0
./cicd-blue-green-deployment.sh result-app 1.0.0
```

### DEV
To run the application locally in dev mode, enter the application folder and use the command in the terminal:

```bash
quarkus dev
```

### Tests
To run unit tests and integration tests, within the project folder you want to test, use the command below:

```bash
./mvnw verify -DskipITs=false -Dquarkus.log.handler.gelf.enabled=false -Dquarkus.opentelemetry.enable=false -Dquarkus.datasource.jdbc.driver=org.mariadb.jdbc.Driver
```
