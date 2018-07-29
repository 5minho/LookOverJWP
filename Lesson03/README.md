### 3장 서블릿 프로그래밍

`CGI`, `Servlet`, `ServletContainer`, `JavaEE`, `Tomcat`의 관계에 대해 공부<br/>
`web.xml`, `Servlet`, `GenericServlet`, `@WebServlet` 에 대해서 공부

### 간단 요약

#### CGI 
 웹 서버와 웹 어플리케이션이 데이터를 주고 받는 규칙
#### Servlet 
 자바로 만든 CGI 프로그램
#### Java EE 
 기업용 어플리케이션, 클라우드 어플리케이션 개발에 필요한 기술들을 정리하고 모아 놓은 것
#### WAS
Java EE 기술들을 구현해 놓은 구현체 (JEUS, WebLogic, JBoss, GlassFish)
#### Servlet Container
Java EE 기술들 중 Servlet, JSP 기술만 구현한 서버 (Tomcat, Jetty)<br/>
Servlet 의 생명주기를 관리 한다 

Java EE 의 각 버젼에 따라 주요 제품들이 있다.
ex ) Java EE 6 -> Servlet 3.0, JSP 2.2, Tomcat 7 ...
> Tomcat 7 은 Servlet Container 또는 Web Container 라고 부르고, Java EE 6 의 기술 중 Web 에 관련한 기술 사양을 준수하여 만든 제품

#### javax.servlet.Servlet Interface 
서블릿 컨테이너가 서블릿에 대해 호출할 메서드를 정의해 놓음

#### Servlet Container 가 요청을 처리하는 순서
1. url에 해당하는 Servlet 을 찾음 (Memory 에서 찾는 걸까???)
2. 없다면 Servlet 클래스 로딩, instance 생성 (init() 호출)
3. service() 호출
4. service() 에서 만든 결과로 응답
5. 시스템 운영자가 Servlet Container (Tomcat) 을 종료하면, destory() 호출

#### GenericServlet
javax.servlet.Servlet Interface 의 메서드 중 `service()` 메서드를 제외하고 구현해둔 추상 클래스

#### web.xml

웹 어플리케이션의 배치 정보를 담고 있는 파일(tomcat 이 클라이언트의 각각 요청에 따라 어떤 서블릿으로 응답해야 할까? 에 대한 정보)

#### @WebServlet 어노테이션
web.xml 을 사용하지 않고 서블릿 배치 정보를 서블릿 컨테이너에 알려주는 방법

##### 주의사항??
문자열데이터 응답시 'response.getWirter()` 호출 <br/>
바이너리 데이터 응답시 `response.getOutputStream()` 호출 <br/>
`getWirter()` 호출시 `setContentType()`, `setCharacterEncoding()` 먼저 호출 해야함