package pack.give.dao;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GiveDao extends AbstractDAO {
    @Autowired
    SqlSession session;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public void insertFile(Map<String, Object> map) throws Exception {
        insert("insertFile", map);
    }

}
