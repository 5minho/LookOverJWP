package spms.listeners; /**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 1..
 */

import spms.controls.*;
import spms.dao.MysqlMemberDao;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.sql.DataSource;

@WebListener()
public class ContextLoaderListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ContextLoaderListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      try {
          ServletContext servletContext = sce.getServletContext();
          InitialContext initialContext = new InitialContext();
          DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");

          MysqlMemberDao mysqlMemberDao = new MysqlMemberDao();
          mysqlMemberDao.setDataSource(dataSource);
          servletContext.setAttribute("/auth/login.do",
                  new LoginController().setMysqlMemberDao(mysqlMemberDao));
          servletContext.setAttribute("/auth/logout.do",
                  new LogoutController());
          servletContext.setAttribute("/member/list.do",
                  new MemberListController().setMysqlMemberDao(mysqlMemberDao));
          servletContext.setAttribute("/member/add.do",
                  new MemberAddController().setMysqlMemberDao(mysqlMemberDao));
          servletContext.setAttribute("/member/update.do",
                  new MemberUpdateController().setMysqlMemberDao(mysqlMemberDao));
          servletContext.setAttribute("/member/delete.do",
                  new MemberDeleteController().setMysqlMemberDao(mysqlMemberDao));
      } catch (Exception e) {
          e.printStackTrace();
      }

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
