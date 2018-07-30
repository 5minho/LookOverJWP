### 4장 서블릿과 JDBC

JDBC<br/> 
자바 웹 어플리케이션과 MySQL을 연동<br/>
HttpServlet 으로 GET, POST 요청처리<br/>

### 간단 요약
<br/>
##### JDBC Driver
자바 프로그램의 요청을 DBMS 프로토콜에 맞추어서 DBMS와 직접 통신하게 해줌

##### java.sql.Connection Interface
이 인터페이스를 구현한 객체는 DB 접속 정보를 다루는 객체 <br/>
이 객체를 통해서 데이터베이스에 SQL을 보내는 객체를 얻을 수 있음 <br/>
commit(), rollback() 메서드로 트랜잭션 처리를 할 수 있음

##### java.sql.Statement Interface
데이터베이스에 SQL문을 보내는 메서드들을 정의하고 있음

##### java.sql.ResultSet Interface
이 인터페이스의 구현체를 통해 서버에서 SQL의 결과를 가져올 수 있음

##### finally {...} 블록에서 자원을 해제할 때는 역순으로 처리

##### HttpServlet
`GenericServlet`의 하위 클래스 service() 메서드가 구현되어 있다.

##### PreparedStatement
PreparedStatement 는 쿼리를 실행할때 미리 컴파일 해둔 쿼리를 사용해서 성능상 이점이 있다. 이미지와 같은 바이너리 데이터를 저장하거나 변경할때는 PreparedStatement 만 가능하다.

| 비교 항목 | Statement | PreparedStatement |
| --- | ---- | --- |
| 실행 속도 | 질의할 때마다 SQL 문 컴파일 | SQL 문을 미리 준비하여 컴파일 해둠. <br/>입력 매개변수 값만 추가하여 서버에 전송. <br/>여러번 반복하여 질의하는 경우 실행속도가 빠름 |
| 바이너리 데이터 전송 | 불가능 | 가능 |
| 프로그래밍 편의성 | sql 문 안에 입력 매개변수 값이 포함되어 있어서 SQL 문이 복잡하고 매개변수가 여러개인 경우 코드 관리가 힘들다| SQL 문과 입력 매개변수가 분리되어 있어서 코드 작성이 편리하다. |

##### Refresh
일정 시간이 지나고 나서 자동으로 서버에 요청을 보내는 방법<br/>
어떤 작업을 하고 작업 결과를 출력할때 사용

두가지 방법으로 구현 가능
* response.addHeader
* meta 태그 추가

##### Redirect
작업 결과를 출력하지 않고 즉시 목록화면으로 이동하게 하는 방법 <br/>
Redirect 정보는 응답헤더에 추가한다.
Response Header 응답 코드는 302 (요청한 자원이 다른 URL로 이동됬음)
Location Header 에 이동할 페이지의 URL이 있음

##### 서블릿 초기화 매개변수
서블릿 컨테이너가 서블릿의 init() 을 호출할 때 서블릿 컨테이너가 전달하는 데이터 <br>

##### 컨텍스트 초기화 매개변수
서블릿 초기화 매개변수는 그 매개변수가 선언된 서블릿에서만 사용될 수 있지만 컨텍스트 초기화 매개변수는 같은 웹 어플리케이션에 소속된 서블릿들이 공유하는 매개변수이다.

##### 필터
서블릿 실행 전후에 어떤 작업을 하고자 할때 사용하는 기술 <br/>
서블릿과 마찬가지로 웹 어플리케이션을 시작할때 init() 을 호출하고 종료될때 destory() 호출
