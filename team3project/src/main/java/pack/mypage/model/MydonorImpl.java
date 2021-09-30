package pack.mypage.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.model.OldBookDto;

import java.util.List;

@Repository
public class MydonorImpl extends SqlSessionDaoSupport {
    @Autowired
    public MydonorImpl(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public List<OldBookDto> donorListbyId(String id) {
        return getSqlSession().selectList("selectGiveList", id);
    }

}
