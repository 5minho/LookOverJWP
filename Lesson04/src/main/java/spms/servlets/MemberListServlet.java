package spms.servlets;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 30..
 */
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/studydb",
                    "study",
                    "study"
            );
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select mno, mname, email, cre_date" + " from members" + " order by mno asc");
            servletResponse.setContentType("text/html; charset=UTF-8");

            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.println("<html><head><title>회원목록</title></head>");
            printWriter.println("<body><h1>회원목록</h1>");
            printWriter.println("<p><a href='add'>신규회원</a></p>");
            while (resultSet.next()) {
                printWriter.println(
                        resultSet.getInt("mno") + ", " +
                                "<a href='update?no=" + resultSet.getInt("MNO") + "'>" +
                        resultSet.getString("mname") + "</a>, " +
                        resultSet.getString("email") + ", " +
                        resultSet.getDate("cre_date") + "<br>");
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            try {if (resultSet != null) resultSet.close();}  catch (Exception e) {}
            try {if (statement != null) statement.close();}  catch (Exception e) {}
            try {if (connection != null) connection.close();}  catch (Exception e) {}
        }
    }
}
