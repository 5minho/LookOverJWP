package spms.dao;

import spms.annotation.Component;
import spms.vo.Project;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 4..
 */

@Component("projectDao")
public class MysqlProjectDao implements ProjectDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Project> selectList() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("" +
                    "select pno, pname, sta_date, end_date, state " +
                    "from projects " +
                    "order by pno desc");
            rs = pstmt.executeQuery();

            List<Project> projects = new ArrayList<>();

            while (rs.next()) {
                Project project = new Project()
                        .setNo(rs.getInt("pno"))
                        .setName(rs.getString("pname"))
                        .setStartDate(rs.getDate("sta_date"))
                        .setEndDate(rs.getDate("end_date"))
                        .setState(rs.getInt("state"));
                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

    }

    @Override
    public int insert(Project project) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("" +
                    "insert into Projects " +
                    "(pname, content, sta_date, end_date, state, cre_date, tags) " +
                    "values (?, ?, ?, ?, 0, now(), ?)");
            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getContent());
            pstmt.setDate(3, new Date(project.getStartDate().getTime()));
            pstmt.setDate(4, new Date(project.getEndDate().getTime()));
            pstmt.setString(5, project.getTags());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    @Override
    public int update(Project project) throws Exception {
        PreparedStatement pstmt = null;
        try (Connection conn = dataSource.getConnection()) {
            pstmt = conn.prepareStatement("" +
                            "update projects " +
                            "set pname=?, content=?, sta_date=?, end_date=?, state=?, tags=? " +
                            "where pno=?");
            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getContent());
            pstmt.setDate(3, new Date(project.getStartDate().getTime()));
            pstmt.setDate(4, new Date(project.getEndDate().getTime()));
            pstmt.setInt(5, project.getState());
            pstmt.setString(6, project.getTags());
            pstmt.setInt(7, project.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Project selectOne(int no) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection()) {
            pstmt = conn.prepareStatement("" +
                        "select pno, pname, content, sta_date, end_date, state, tags " +
                        "from projects " +
                        "where pno=?");
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Project()
                        .setNo(rs.getInt("pno"))
                        .setName(rs.getString("pname"))
                        .setContent(rs.getString("content"))
                        .setStartDate(rs.getDate("sta_date"))
                        .setEndDate(rs.getDate("end_date"))
                        .setState(rs.getInt("state"))
                        .setTags(rs.getString("tags"));
            }
            throw new Exception("해당 프로젝트는 존재하지 않습니다.");
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
    }
}
