# Stream your PostgreSQL changes into Kafka with Debezium

This setup is going to demonstrate how to receive Postgres changes into kafka using Debezium

## Table of Contents

* [Topology](#topology)
* [Usage](#usage)

### Topology

```
                          |
                          |
                          |
                  +-------v--------+
                  |                |
                  |   PostgreSQL   |
                  |                |
                  +----------------+
                          |
                          |
                          |
          +---------------v------------------+
          |                                  |
          |           Kafka Connect          |
          |  (Debezium, JDBC connectors)     |
          |                                  |
          +---------------+------------------+
                          |
                          |
                          |
                          |
                  +-------v--------+
                  |                |
                  |     Kafka      |
                  |                |
                  +----------------+
                          |
                          |
           ---------------------------------
          |                                 |
  +-------v--------+                +-------v--------+
  |                |                |                |
  |     Consumer   |                |   Consumer     |
  |                |                |                |
  +----------------+                +----------------+


```
We are using Docker Compose to deploy following components
* PostgreSQL
* Kafka
  * ZooKeeper
  * Kafka Broker
  * Kafka Connect with [Debezium](https://debezium.io/) and  [PostgreSQL](https://github.com/debezium/debezium/tree/main/debezium-connector-postgres) Connectors
  * Schema Registry
  * Kafka Rest
* Debezium UI

### Usage

How to run:

```shell
# Start the application
[Optional]
 docker kill $(docker ps -q)
 docker rm $(docker ps -a -q)
 docker rmi $(docker images -q)
 docker system prune -a
 docker ps
 docker images
 
 docker compose up -d
 docker ps
 check if all containers are running
 
 Docker Kafka CLI
 Check topics created
 kafka-topics --bootstrap-server localhost:9092 --list
 
 Postgres CLI

 psql -U postgres -d students_db -w
 CREATE TABLE student (id integer primary key, name varchar);
 SELECT * FROM student;
 ALTER TABLE public.student REPLICA IDENTITY FULL
 SELECT * FROM student;
 SELECT * FROM student;
  
 curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @source.json
 
 curl -X GET http://localhost:8083/connectors
 curl -X GET http://localhost:8083/connectors/studentsdb-connector 
 curl -X GET http://localhost:8083/connectors/studentsdb-connector/status

 insert into student (id, name) values (1, 'Bangali');
 
 Check topics created
 kafka-topics --bootstrap-server localhost:9092 --list

 
 kafka-console-consumer --bootstrap-server localhost:9092 --topic postgres.public.student --from-beginning 

```
#### Troubleshoot
<p>

    if any docker services is not running
    docker run image
    Check the log
    Solution - Recommendation to get docker-compose from Official Docker Image (Community Version)
    https://github.com/confluentinc/cp-all-in-one/blob/latest/cp-all-in-one/docker-compose.yml

</p>

End application:

```shell
# Shut down the cluster
docker compose -f docker-compose.yaml down
````````

[kafka listeners explained](https://github.com/confluentinc/cp-all-in-one/blob/latest/cp-all-in-one/docker-compose.yml)
[https://www.iamninad.com/posts/docker-compose-for-your-next-debezium-and-postgres-project/](https://github.com/confluentinc/cp-all-in-one/blob/latest/cp-all-in-one/docker-compose.yml)