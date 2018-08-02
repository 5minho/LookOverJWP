package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySQLMemberDao;
import spms.vo.Member;

// ServletContext에 보관된 MySQLMemberDao 사용하기
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(
      HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      request.setAttribute("viewURL", "/member/MemberForm.jsp");
  }

  @Override
  protected void doPost(
      HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      ServletContext sc = this.getServletContext();
      MySQLMemberDao memberDao = (MySQLMemberDao)sc.getAttribute("memberDao");
      memberDao.insert((Member)request.getAttribute("member"));
      request.setAttribute("viewURL", "redirect:list.do");
    } catch (Exception e) {
        throw new ServletException(e);
    }
  }
}
