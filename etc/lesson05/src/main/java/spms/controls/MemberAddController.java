package spms.controls;

import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 1..
 */
public class MemberAddController implements Controller {

    private MysqlMemberDao mysqlMemberDao;

    public MemberAddController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("member") == null) {
            return "/member/MemberForm.jsp";
        }
        Member member = (Member) model.get("member");
        mysqlMemberDao.insert(member);
        return "redirect:list.do";
    }
}
