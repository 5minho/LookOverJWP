package spms.dao;

import spms.annotation.Component;
import spms.vo.Project;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
