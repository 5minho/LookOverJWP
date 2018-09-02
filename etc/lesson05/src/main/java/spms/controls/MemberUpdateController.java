package spms.controls;

import spms.dao.MysqlMemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */
public class MemberUpdateController implements Controller {

    private MysqlMemberDao mysqlMemberDao;

    public MemberUpdateController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("member") == null) {
            Member member = mysqlMemberDao.selectOne((Integer.parseInt((String)model.get("no"))));
            model.put("member", member);
            return "/member/MemberUpdateForm.jsp";
        }
        Member member = (Member) model.get("member");
        mysqlMemberDao.update(member);
        return "redirect:list.do";
    }
}
