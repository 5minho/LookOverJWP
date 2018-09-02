package spms.servlets;

import spms.controls.*;
import spms.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 1..
 */

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String servletPath = req.getServletPath();

        ServletContext sc = getServletContext();
        Map<String, Object> model = new HashMap<>();

        try {
            Controller pageController = (Controller) sc.getAttribute(servletPath);
            switch (servletPath) {
                case "/member/add.do":
                    if (req.getParameter("email") != null) {
                        Member member = new Member()
                                .setEmail(req.getParameter("email"))
                                .setName(req.getParameter("name"))
                                .setPassword(req.getParameter("password"));
                        model.put("member", member);
                    }
                    break;
                case "/member/update.do":
                    if (req.getParameter("email") != null) {
                        Member member = new Member()
                                .setNo(Integer.parseInt(req.getParameter("no")))
                                .setEmail(req.getParameter("email"))
                                .setName(req.getParameter("name"));
                        model.put("member", member);
                    }
                    model.put("no", req.getParameter("no"));
                    break;
                case "/member/delete.do":
                    model.put("no", req.getParameter("no"));
                    break;
                case "/auth/login.do":
                    String email = req.getParameter("email");
                    String password = req.getParameter("password");
                    if (email != null && password != null) {
                        HttpSession session = req.getSession();
                        model.put("email", email);
                        model.put("password", password);
                        model.put("session", session);
                    }
                    break;
                case "/auth/logout.do":
                    model.put("session", req.getSession());
                    break;
            }
            String viewURL = pageController.execute(model);

            for(String key : model.keySet()) {
                req.setAttribute(key, model.get(key));
            }

            if (viewURL.startsWith("redirect:")) {
                resp.sendRedirect(viewURL.substring(9));
                return;
            }

            RequestDispatcher rd = req.getRequestDispatcher(viewURL);
            rd.include(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }

}
