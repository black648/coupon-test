쿠폰 선착순 발급 테스트

* 일반적인 수행 시 Race Condition 이 발생함.
   * Race Condition : 2개 이상의 Thread 에서 공유데이터에 Access 할때 발생

* Redis를 이용한 해결 방법
   * Redis는 싱글 쓰레드 기반이라 Race Condition 을 해결 할 수 있을 뿐만 아니라 incr 방식을 사용하면 성능도 어느정도 커버 가능
   * Redis를 이용하였을 때 대규모 트래픽이 발생한다면 RDB의 CPU 사용량이 급증하여 성능저하 후 오류가 발생 할 수 있음. 
* KafKa를 이용한 해결 방법
  * 테스트
    * 토픽생성 : docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic testTopic
    * 프로듀서 실행 : docker exec -it kafka kafka-console-producer.sh --topic testTopic --broker-list 0.0.0.0:9092
    * 컨슈머 실행 : docker exec -it kafka kafka-console-consumer.sh --topic testTopic --bootstrap-server localhost:9092

    * ApplyServiceTest
      * Redis 초기화 
        * docker exec -it ContainerId redis-cli
        * flushall
      * Topic 생성
        -> docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic coupon_create
      * Consumer 실행 
        -> docker exec -it kafka kafka-console-consumer.sh --topic coupon_create --bootstrap-server localhost:9092 --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"