# 스프링 부트, JPA 환경에서 장소 검색 기능 만들기  

구현 환경  
Spring boot  
Java 1.8  
Maven  
JPA  

사용한 오픈 소스 목록  
bootstrap  
handlebars  
jquery  
jackson-api  
H2 Database  


build 명령어   
mvn -Dmaven.test.skip=true clean package  


jar 파일 구동
java -jar -Dspring.profiles.active=local springbootmap-0.0.1-SNAPSHOT.jar


h2 Base 접속 경로  
/h2-console
