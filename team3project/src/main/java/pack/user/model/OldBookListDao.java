package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.UserDto;

@Repository
public class OldBookListDao extends SqlSessionDaoSupport {

    public OldBookListDao(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public UserDto bestRead() {
        return getSqlSession().selectOne("selectBestRead");
    }

}
