package spms.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 31..
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            ServletContext servletContext = this.getServletContext();
            Class.forName(servletContext.getInitParameter("driver"));
            connection = DriverManager.getConnection(
                    servletContext.getInitParameter("url"),
                    servletContext.getInitParameter("username"),
                    servletContext.getInitParameter("password")
            );
            preparedStatement = connection.prepareStatement("UPDATE members set email=?, mname=?, mod_date=now()"
                    + " where mno=?");
            preparedStatement.setString(1, request.getParameter("email"));
            preparedStatement.setString(2, request.getParameter("name"));
            preparedStatement.setString(3, request.getParameter("no"));
            preparedStatement.executeUpdate();

            response.sendRedirect("list");
        } catch (Exception e) {

        } finally {
            try {if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try {if (connection != null) connection.close();} catch (Exception e) {}
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            ServletContext servletContext = this.getServletContext();
            Class.forName(servletContext.getInitParameter("driver"));
            connection = DriverManager.getConnection(
                    servletContext.getInitParameter("url"),
                    servletContext.getInitParameter("username"),
                    servletContext.getInitParameter("password")
            );
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select mno, email, mname, cre_date from members"
                            + " where mno=" + request.getParameter("no")
            );
            resultSet.next();

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter printWriter = response.getWriter();
            printWriter.println("<html><head><title>회원정보</title></head>");
            printWriter.println("<body><h1>회원정보</h1>");
            printWriter.println("<form action='update' method='post'>");
            printWriter.println("번호 : <input type='text' name='no' value='"
                    + request.getParameter("no") + "'readonly><br>");
            printWriter.println("이름: <input type='text' name='name'" + " value='"
                    + resultSet.getString("MNAME") + "'><br>");
            printWriter.println("이메일: <input type='text' name='email'" + " value='"
                    + resultSet.getString("EMAIL") + "'><br>");
            printWriter.println("<input type='submit' value='저장'>");
            printWriter.println("<input type='button' value='취소'" + " onclick='location.href=\"list\"'>");
            printWriter.println("</form>");
            printWriter.println("</body></html>");

        } catch (Exception e) {
            new ServletException(e);
        } finally {
            try { resultSet.close(); } catch (Exception e) {}
            try { statement.close(); } catch (Exception e) {}
            try { connection.close(); } catch (Exception e) {}
        }
    }
}
