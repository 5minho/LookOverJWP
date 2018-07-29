package servlets;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 30..
 */
@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        int a = Integer.parseInt(servletRequest.getParameter("a")); //GET, POST 요청으로 넘어온 파라미터를 꺼냄
        int b = Integer.parseInt(servletRequest.getParameter("b"));

        servletResponse.setContentType("text/plain");
        servletResponse.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = servletResponse.getWriter();

        printWriter.println("a=" + a + ", " + "b=" + b + "의 계산결과 입니다.");
        printWriter.println("a + b = " + (a + b));
    }
}
