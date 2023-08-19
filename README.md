쿠폰 선착순 발급 테스트

* 일반적인 수행 시 Race Condition 이 발생함.
   * Race Condition : 2개 이상의 Thread 에서 공유데이터에 Access 할때 발생

* Redis를 이용한 해결 방법
   * Redis는 싱글 쓰레드 기반이라 Race Condition 을 해결 할 수 있을 뿐만 아니라 incr 방식을 사용하면 성능도 어느정도 커버 가능
   * Redis를 이용하였을 때 대규모 트래픽이 발생한다면 RDB의 CPU 사용량이 급증하여 성능저하 후 오류가 발생 할 수 있음. 