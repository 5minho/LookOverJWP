package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.Map;

/**R
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 5..
 */

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {"project", spms.vo.Project.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project) model.get("project");
        if (project.getContent() == null) {
            return "/project/ProjectForm.jsp";
        }
        if (projectDao.insert(project) <= 0) {
            throw new Exception("fail project insert");
        }
        return "redirect:list.do";
    }
}
