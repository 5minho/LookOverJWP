package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.dao.MysqlProjectDao;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 7..
 */

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "project", spms.vo.Project.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project) model.get("project");
        if (project.getContent() == null) {
            project = projectDao.selectOne(project.getNo());
            model.put("project", project);
            return "/project/ProjectUpdateForm.jsp";
        }

        projectDao.update(project);
        return "redirect:list.do";
    }
}
