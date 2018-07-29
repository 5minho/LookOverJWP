package servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 30..
 */
public class HelloServlet implements Servlet {
    ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init() 호출됨");
        // 서블릿 컨테이너가 서블릿을 생성한 후 초기화 작업을 수행하기 위한 메서드
        // 서블릿이 클라이언트의 요청을 처리하기 전에 준비할 작업이 있다면 여기다가 작성
        // ex) DB 연결, 외부 스토리지와의 서버 연결, 프로퍼티 로딩 등등
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig() 호출됨");
        // 서블릿 설정 정보를 다루는 servletConfig 객체 반환
        // 서블릿의 이름, 초기 매개변수 값, 환경정보 등등
        return this.getServletConfig();
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service() 호출됨");
        // 실질적인 서비스 작업을 수행하는 메서드
    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo() 호출됨");
        // 서블릿 버전, 권리 등등을 담은 정보를 문자열 형태로 리턴
        return "version 1.0";
    }

    @Override
    public void destroy() {
        System.out.println("destroy 호출됨");
        // 컨테이너가 종료되거나 웹 어플리케이션이 멈출 때, 또는 서블릿을 비 활성화 시킬때 호출
        // 데이터 자원 해제, 데아터를 저장 등 마무리 작업 실행
    }
}
