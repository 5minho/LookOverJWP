### MVC 아키텍쳐

MVC 아키텍쳐의 필요성,  MVC 각 컴포넌트의 역할에 대해 공부, JSP, VO, DTO, Forward, Include, ServletContext, HttpSession, HttpServletRequest, Listener, DataSource, JNDI

### 간단 요약

#### 왜 MVC??
시스템 변경이 잦은 상황에서 유지 보수를 쉽게하기 위해

* 중복코드 최소화
* 코드 변경의 용이성
* 기존코드의 재사용

등을 고려해야함. 이러한 것들을 충족 시키기 위해 아키텍쳐 디자인 패턴을 적용 해야하는데 그 중 `MVC 디자인 패턴`은 다년간 어플리케이션에 적용되어 좋다고 증명이 되어 있음.

#### Model
DBMS 와 통신하여 사용자에게 보여줄 데이터를 가공하는 일을함 <br/>
#### Controller
클라이언트의 요청을 받았을 때 그 요청에 대한 처리 코드가 있는 Model 컴포넌트를 호출해줌. <br/>
호출시 데이터를 쉽게 전달하기 위해 적절히 가공함
#### View
Model 이 처리한 데이터를 사용해 화면을 만듦 (Html, CSS, JavaScript)
#### JSP의 구동원리
1. 클라이언트가 JSP 파일을 요청하면, 서블릿 컨테이너는 JSP 파일에 대응하는 자바 서블릿을 찾아서 실행
2. JSP에 대응하는 서블릿이 없거나 JSP가 변경되었으면 JSP 엔진을 통해 JSP 파일을 해석하여 서블릿 자바 소스를 생성
3. 서블릿 자바 소스는 자바 컴파일러를 통해 서블릿 클래스 파일로 컴파일
4. JSP로 부터 생성된 서블릿을 서블릿 구동 방식에 따라 실행 (service() 메서드를 통해 HTML 화면을 웹브라저로 보냄)

JSP 엔진은 JSP 파일로 서블릿 클래스를 생성할때 `HttpJspPage` 인터페이스를 구현한 클래스를 만든다. `HttpJspPage` 인터페이스는 `Servlet` 인터페이스를 상속 받고 있기 때문에 결국 이 클래스도 서블릿이다.

#### VO == DTO == DO
데이터베이스에서 가지고온 데이터를 객체에 담아 JSP 페이지에 전달한다.
이러한 용도의 객체를 VO (Value Object), DTO (Data Transfer Object), DO (Domain Object) 라고 부른다.

#### RequestDispatcher
다른 서블릿이나 JSP 로 작업을 위임할 때 사용하는 객체 <br/>
HttpServletRequest 객체를 통해서 얻는다.

#### Forward, Include

*  Forward : 특정 서블릿을 포워드 하면 그 서블릿으로 제어권을 넘긴 후 작업을 끝내면 다시 제어권이 넘어오지 않는다.
*  Include :  특정 서블릿을 인클루드 하면 그 서블릿이 작업을 끝내고 다시 제어권이 넘어온다. 

#### Servlet 들이 데이터를 공유하는 방법

* ServletContext : 웹 어플리케이션 시작 - 종료 까지 유지, JSP 에서 application 변수로 참조할 수 있음
* HttpSession : 클라이언트 요청-브라우저 종료 까지 유지, JSP 에서 session 변수로 참조할 수 있음
* ServletRequest : 클라이언트 요청-응답 까지 유지, JSP 에서 request 변수로 참조할 수 있음
* JspContext : Jsp 페이지를 실행하는 동안 유지, JSP 에서 pageContext 변수를 통해 참조할 수 있음

#### DAO 
데이터 처리를 전문으로 하는 객체를 DAO(Data Access Object) 라고 함.

DAO는 데이터베이스, 파일, 메모리 등을 이용하여 어플리케이션 데이터를 생성, 조회, 변경 삭제하는 역할을 수행

보통 하나의 DB 테이블에 대응, 여러 테이블을 조인한 데이터를 다룸

#### Listener
웹 어플리케이션의 상태를 모니터링 할 수 있도록 제공되는 인터페이스 <br/>
`@WebListner` 어노테이션을 붙이는 방법과 `web.xml` 에 명시하는 방법이 있음

#### ConnectionPool
여러 요청이 왔을때 하나의 DB Connection 으로 다수의 요청을 처리하기에는 문제가 있다. DB Connection 은 commit, rollback 같은 트랜잭션을 처리하기 때문이다. 그래서 요청마다 Connection 이 필요한데. 요청이 왔을때 DBMS 와 Connection 을 맺는다면
성능이 저하 된다. (커넥션을 맺을때 네트워킹, 인증 등이 필요함) 그래서 여러 Connection을 미리 만들어 놓고 필요할 때 가져다 쓰고 반납하고 하는 것을 ConnectionPooling 이라고 한다.

#### DataSource Interface
Java EE 서버 (ex : Tomcat) 에서 DB Connection Pool 을 관리하는 방법 (따로 Connection Pool 을 구현하지 않아도 된다.)

javax.sql 패키지에 정의되어 있다.

javax.sql 패키지 의 기능
* Connection 및 Statement 객체의 풀링
* 분산 트랜잭션 처리
* Rowsets의 지원
 에 정의 되어 있다.
 
 
 #### JNDI (Java Naming and Directory Interface API)
 서버에서 관리되는 자원(DataBase, Messging )을 찾기위한 API 
 디렉터리 형식으로 자원을 서버에 등록(Tomcat 의 context.xml)해 놓고 web.xml에 자원을 참조한다고 명시한다.
 
 어플리케이션에서는 등록한 자원의 이름(디렉터리 형식)을 이용해 그 자원을 참조한다.
 
 ### 추가 공부
 
 HttpSession 이 어떻게 세션을 유지 시키는지 궁금해서 찾아 보았다. (브라우저가 종료되었는지 어떻게 알까?)
 
 
1. 첫번째 request 에 서블릿 컨테이너는 SessionID 를 내부에 만들어서 response 시 만들어 놓은 SessionID를 같이 전송한다. 
2. 다음 request 에 위에서 전달받은 SessionID 를 같이 전달한다. 서블릿 컨테이너는 전달받은 SessionID 를 참조해서 세션을 판단한다.

브라우저에서 SessionID 는 쿠키로 저장하고 브라우저를 닫게 되면 저장했던 SessionID는 사라진다 (max-age 또는 Expires 를 지정하지 않았기 때문) 이런 일시적인 쿠키를 `세션쿠키`라 한다
