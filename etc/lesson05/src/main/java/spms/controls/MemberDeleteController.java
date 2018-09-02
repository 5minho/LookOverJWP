package spms.controls;

import spms.dao.MysqlMemberDao;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */
public class MemberDeleteController implements Controller {

    private MysqlMemberDao mysqlMemberDao;

    public MemberDeleteController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        int no = Integer.parseInt((String)model.get("no"));
        mysqlMemberDao.delete(no);
        return "redirect:list.do";
    }
}
