package spms.listeners;

// 서버에서 제공하는 DataSource 사용하기
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import spms.controllers.MemberAddController;
import spms.controllers.MemberListController;
import spms.dao.MySQLMemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    try {
      ServletContext sc = event.getServletContext();
      
      InitialContext initialContext = new InitialContext();
      DataSource ds = (DataSource)initialContext.lookup(
          "java:comp/env/jdbc/studydb");
      
      MySQLMemberDao memberDao = new MySQLMemberDao();
      memberDao.setDataSource(ds);
      
      sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
      sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));

    } catch(Throwable e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {}
}
