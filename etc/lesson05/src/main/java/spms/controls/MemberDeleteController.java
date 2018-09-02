package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {

    private MysqlMemberDao mysqlMemberDao;

    public MemberDeleteController setMysqlMemberDao(MysqlMemberDao mysqlMemberDao) {
        this.mysqlMemberDao = mysqlMemberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        int no = (Integer) model.get("no");
        mysqlMemberDao.delete(no);
        return "redirect:list.do";
    }
}
