package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySQLMemberDao;

// ServletContext에 보관된 MySQLMemberDao 사용하기
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			MySQLMemberDao memberDao = (MySQLMemberDao)sc.getAttribute("memberDao");

			request.setAttribute("members", memberDao.selectList());
			request.setAttribute("viewURL", "/member/MemberList.jsp");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
