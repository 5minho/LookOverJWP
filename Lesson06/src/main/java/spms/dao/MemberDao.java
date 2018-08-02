package spms.dao;

import spms.vo.Member;

import java.util.List;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 2..
 */
public interface MemberDao {
    List<Member> selectList() throws Exception;
    int insert(Member member) throws Exception;
    int delete(int no) throws Exception;
    Member selectOne(int no) throws Exception;
    int update(Member member) throws Exception;
    Member exist(String email, String password) throws Exception;
}
