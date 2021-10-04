package pack.give.dao;

import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GiveDao extends SqlSessionDaoSupport {
    public GiveDao(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public void insertFile(Map<String, Object> map) {
        getSqlSession().insert("insertFile", map);
    }

}
