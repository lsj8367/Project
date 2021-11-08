package pack.mypage.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.oldbook.model.OldBookDto;

@Repository
public class MyrentImpl extends SqlSessionDaoSupport {

    @Autowired
    public MyrentImpl(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public List<OldBookDto> rentlistall(String userid) {
        return getSqlSession().selectList("rentlistall", userid);
    }

}
