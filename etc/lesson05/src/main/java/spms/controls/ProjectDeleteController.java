package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import sun.misc.Contended;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 8..
 */

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {

    ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no" , Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        int no = (Integer) model.get("no");
        projectDao.delete(no);
        return "redirect:list.do";
    }
}
