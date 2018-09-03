package spms.controls;

import spms.annotation.Component;
import spms.dao.ProjectDao;
import spms.vo.Project;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 4..
 */

@Component("/project/list.do")
public class ProjectListController implements Controller {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        List<Project> projects = projectDao.selectList();
        model.put("projects", projects);
        return "/project/ProjectList.jsp";
    }
}
