package spms.dao;

import spms.vo.Project;

import java.util.List;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 4..
 */
public interface ProjectDao {
    List<Project> selectList() throws Exception;
    int insert(Project project) throws Exception;
    int update(Project project) throws Exception;
    Project selectOne(int no) throws Exception;
    int delete(int no) throws Exception;
}
