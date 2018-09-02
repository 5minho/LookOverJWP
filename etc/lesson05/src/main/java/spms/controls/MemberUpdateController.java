package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {

    private MysqlMemberDao mysqlMemberDao;

    public MemberUpdateController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "member", spms.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) {
            member = mysqlMemberDao.selectOne((Integer)model.get("no"));
            model.put("member", member);
            return "/member/MemberUpdateForm.jsp";
        }
        member = (Member) model.get("member");
        mysqlMemberDao.update(member);
        return "redirect:list.do";
    }
}
