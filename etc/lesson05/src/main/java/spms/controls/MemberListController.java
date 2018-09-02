package spms.controls;


import spms.annotation.Component;
import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import java.util.List;
import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 1..
 */

@Component("/member/list.do")
public class MemberListController implements Controller {

    private MysqlMemberDao mysqlMemberDao;

    public MemberListController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        List<Member> members = mysqlMemberDao.selectList();
        model.put("members", members);
        return "/member/MemberList.jsp";
    }

}
