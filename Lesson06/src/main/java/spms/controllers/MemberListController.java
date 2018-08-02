package spms.controllers;

import spms.dao.MySQLMemberDao;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 2..
 */
public class MemberListController implements Controller {

    private MySQLMemberDao memberDao;

    public MemberListController setMemberDao(MySQLMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
}
