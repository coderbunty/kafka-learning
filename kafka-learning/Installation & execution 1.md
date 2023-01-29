### Installation & execution (1 broker & 1 zoo keeper)

[https://www.conduktor.io/kafka/how-to-install-apache-kafka-on-mac](https://www.conduktor.io/kafka/how-to-install-apache-kafka-on-mac)
[[References]]

##### Installation
1.  Install Java 11

```
Java 11 JDK HOME path —> /Users/abhisheksharma/Library/Java/JavaVirtualMachines/corretto-11.0.16/Contents/Home

$ vim .zprofile

$ export JAVA_HOME=/Users/abhisheksharma/Library/Java/JavaVirtualMachines/corretto-11.0.16/Contents/Home  

$ echo $JAVA_HOME

$ java -version  
```

2. Install & extract Kafka binaries - [https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)  

##### Starting Kafka on local machine (using 1 broker & 1 zoo keeper)

1.  Ensure JAVA_HOME points to Java 11

   ```
$ echo $JAVA_HOME

       $ java -version  
        
      If version is not 11, then change it.  
       $ export JAVA_HOME=/Users/abhisheksharma/Library/Java/JavaVirtualMachines/corretto-11.0.16/Contents/Home  
```

2.  Start zookeeper  
```
$ cd ~/Workarea/kafka_2.13-3.2.1  
    $ ./bin/zookeeper-server-start.sh config/zookeeper.properties  
```

3.  Start kafka  
   ```
$ cd ~/Workarea/kafka_2.13-3.2.1  
       $ ./bin/kafka-server-start.sh config/server.properties
```

We can also add the root kafka path to PATH, so that we don’t have to type in the full paths for kafka commands.  
```
$ vim ~/.zshrc  
$ PATH="$PATH:/Users/abhisheksharma/Workarea/kafka_2.13-3.2.1/bin"  
$ source ~/.zshrc
```
Eg: **kafka-topics.sh --bootstrap-server localhost:9092 --list**
  

##### Kafka CLI

1.  List topic  
```
kafka-topics.sh --bootstrap-server localhost:9092 --list  
```

2.  Create a new topic  
```
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic demo_topic

kafka-topics.sh --bootstrap-server localhost:9092 --create --topic demo_topic --partitions 3

kafka-topics.sh --bootstrap-server localhost:9092 --create --topic demo_topic --partitions 3 --replication-factor 1
```

3.  Describe a topic  
```
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic demo_topic 
``` 

4.  Producing to a topic  
```
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic demo_topic
```  
    > Hello World  
    >My name is Conduktor  
    >I love Kafka  
    >^C  (<- Ctrl + C is used to exit the producer)  
      
Producing with keys to a topic  
```
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic demo_topic --property parse.key=true --property key.separator=:
```
      >example key:example value

      >name:Stephane  

5.  Consuming from the end (default)  
```
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_topic
```  

Consuming from the beginning
```
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_topic --from-beginning
```  

Consuming from the beginning along with the key & timestamp
```
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_topic --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning
```  
    
6.  One consumer or multiple consumers can consume the same topic. In this scenario, if there are multiple partitions to the topic, then the same consumer will have to pull the data from all those partitions.
   
    To speed up the data pulling, we can have a consumer group (m) for one topic having multiple partitions (n). If m < n, then few consumer will be pulling data from more than one partition, if n == m, then every consumer in the group will pull from one partition each, if m > n, then (m-n) consumers will be idle while others will pull data from one partition each.  
      
    Pulling the data from topic partitions by consumers in a consumer group will be mutually exclusive.  
      
    Below we are starting a consumer group with 2 consumers who will non overlappingly read from the demo_topic partitions,
```
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_topic --group demo-consumer-group --from-beginning  

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_topic --group demo-consumer-group --from-beginning
```  

We can also start one more consumer group to read from the same topic, now the data will be read in both consumer groups.  
   
7.  List consumer groups  
```
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```        

Describe a consumer group  
```
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group demo-consumer-group --describe
```  
      
Note: Even if we have only one consumer & do not create a consumer group, by default, a temporary consumer group will be assigned to this consumer, this consumer group will get deleted once the consumer stops consuming. This temporary consumer group will be named like console-consumer-69113.  
    
8.  We know that consumers read from a consumer group & they commit offset once they have read data, this allows the consumers to read from where the offset was last committed.  
    There is a mechanism where in we can change these offsets.  
      
    Reset offsets to start / earliest of each partition,  
```
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group demo-consumer-group --topic demo_topic --execute --reset-offsets --to-earliest
```  
      
Shift offset by 2 (forward)  
```
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group demo-consumer-group --reset-offsets --shift-by 2 --execute --topic demo_topic
```  

Shift offset by 2 (backward)  
```
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group demo-consumer-group --reset-offsets --shift-by -2 --execute --topic demo_topic
```

