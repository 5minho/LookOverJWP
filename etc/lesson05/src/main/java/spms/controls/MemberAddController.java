package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 1..
 */

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {

    private MysqlMemberDao mysqlMemberDao;

    public MemberAddController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", spms.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) {
            return "/member/MemberForm.jsp";
        }
        mysqlMemberDao.insert(member);
        return "redirect:list.do";
    }
}
