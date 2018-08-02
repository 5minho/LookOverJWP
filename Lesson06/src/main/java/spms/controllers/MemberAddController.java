package spms.controllers;

import spms.bind.DataBinding;
import spms.dao.MySQLMemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 2..
 */
public class MemberAddController implements Controller, DataBinding {

    private MySQLMemberDao memberDao;

    public MemberAddController setMemberDao(MySQLMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) {
            return "/member/MemberForm.jsp";
        }
        memberDao.insert((Member)model.get("member"));
        return "redirect:list.do";
    }

    @Override
    public Object[] getDataBindings() {
        return new Object[]{
                "member", spms.vo.Member.class
        };
    }
}
