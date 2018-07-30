package spms.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 30..
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/studydb",
                    "study",
                    "study"
            );

            preparedStatement = connection.prepareStatement(
                    "insert into members(email, pwd, mname, cre_date, mod_date)"
                    + " values (?,?,?,now(),now())"
            );

            preparedStatement.setString(1, request.getParameter("email"));
            preparedStatement.setString(2, request.getParameter("password"));
            preparedStatement.setString(3, request.getParameter("name"));
            preparedStatement.executeUpdate();

            response.sendRedirect("list");

//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter printWriter = response.getWriter();
//            printWriter.println("<html><head><title>회원등록결과</title></head>");
//            printWriter.println("<body>");
//            printWriter.println("<p>등록 성공 입니다!</p>");
//            printWriter.println("</body></html>");

        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            try {preparedStatement.close();} catch (Exception e) {}
            try {connection.close();} catch (Exception e) {}
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html><head><title>회원 등록</title></head>");
        printWriter.println("<body><h1>회원 등록</h1>");
        printWriter.println("<form action='add' method='post'>");
        printWriter.println("이름: <input type='text' name='name'><br>");
        printWriter.println("이메일: <input type='text' name='email'><br>");
        printWriter.println("암호: <input type='password' name='password'><br>");
        printWriter.println("<input type='submit' value='추가'>");
        printWriter.println("<input type='reset' value='취소'>");
        printWriter.println("</form>");
        printWriter.println("</body></html>");
    }
}
