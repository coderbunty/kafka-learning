### Docker container installation & execution

##### Installation
This is similar to [https://hub.docker.com/r/landoop/fast-data-dev](https://hub.docker.com/r/landoop/schema-registry-ui/), was done for the compatibility with Mac M1 chip [https://github.com/lensesio/fast-data-dev/issues/175#issuecomment-947001807].

```
$ git clone https://github.com/faberchri/fast-data-dev.git

$ cd fast-data-dev

$ docker build -t faberchri/fast-data-dev .

$ docker run --name kafka-suite --rm -p 2181:2181 -p 9092:9092 -p 3030:3030 -e ADV_HOST=127.0.0.1 faberchri/fast-data-dev

(add -d/--detach to run container in background and print container ID)

$ docker run --name kafka-suite -d --rm -p 2181:2181 -p 9092:9092 -p 3030:3030 -e ADV_HOST=127.0.0.1 faberchri/fast-data-dev


Kafka broker - port 9092

Zookeeper - port 2181

Schema registry - 8081

Kafka Rest proxy - 8082

Kafka Connect - 8083

Web server (UI) - 3030 ( [http://127.0.0.1:3030](http://127.0.0.1:3030) )
```