< 회의실 예약 어플리케이션 >

1. 문제해결전략

 - 시간값을 어떤 식으로 저장할 것인가?
  30분을 기준값으로 하루 24시간을 30분씩 48개로 나누어 가져가는 방식으로 예약.
  30분이라는 기준을 하나의 UNIT 으로 보아 추후 UNIT 변경이 가능한 구조로 제작.
  
  - 동일 시간 동시 예약 시도자에 대한 판단?
   DB 의 unique 값을 이용하여 exception 을 받아 처리.
   
  - 회의실이름은 추후 추가와 중복 방지를 위해 따로 관리.
  
  
2. 프로젝트 빌드, 실행방법

  - project download
  - mvnw clean package
  - java -jar room.jar 
