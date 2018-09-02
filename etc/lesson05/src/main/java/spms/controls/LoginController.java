package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */

@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {

    private MysqlMemberDao mysqlMemberDao;

    public LoginController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", spms.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member loginInfo = (Member) model.get("loginInfo");
        if (loginInfo.getEmail() == null || loginInfo.getPassword() == null) {
            return "/auth/LoginForm.jsp";
        }
        Member member = mysqlMemberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());
        if (member != null) {
            HttpSession session = (HttpSession) model.get("session");
            session.setAttribute("member", member);
            return "redirect:../member/list.do";
        }
        return "/auth/LoginFail.jsp";
    }
}
