package spms.controls;

import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */
public class LoginController implements Controller{

    private MysqlMemberDao mysqlMemberDao;

    public LoginController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        String email = (String) model.get("email");
        String password = (String) model.get("password");
        if (email == null || password == null) {
            return "/auth/LoginForm.jsp";
        }
        Member member = mysqlMemberDao.exist(email, password);
        if (member != null) {
            HttpSession session = (HttpSession) model.get("session");
            session.setAttribute("member", member);
            return "redirect:../member/list.do";
        }
        return "/auth/LoginFail.jsp";
    }
}
