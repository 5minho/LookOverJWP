package spms.servlets;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.controllers.Controller;
import spms.controllers.MemberAddController;
import spms.controllers.MemberListController;
import spms.vo.Member;

import javax.jws.Oneway;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 2..
 */

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String servletPath = req.getServletPath();
        HashMap<String, Object> model = new HashMap<>();
        Controller pageController = (Controller) getServletContext().getAttribute(servletPath);

        try {
            if (pageController instanceof DataBinding) {
                prepareRequestData(req, model, (DataBinding)pageController);
            }

            String viewURL = pageController.execute(model);

            for (String key : model.keySet()) {
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

    private void prepareRequestData(HttpServletRequest req,
                                    HashMap<String, Object> model,
                                    DataBinding dataBinding) throws Exception {
        Object[] dataBinders = dataBinding.getDataBindings();
        String dataName;
        Class<?> dataType;
        Object dataObj;

        for (int i = 0 ; i < dataBinders.length; i += 2) {
            dataName = (String) dataBinders[i];
            dataType = (Class<?>) dataBinders[i + 1];
            dataObj = ServletRequestDataBinder.bind(req, dataType, dataName);
            model.put(dataName, dataObj);
        }
    }
}
