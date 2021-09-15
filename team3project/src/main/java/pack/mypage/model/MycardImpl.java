package pack.mypage.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MycardImpl extends SqlSessionDaoSupport {
	
	@Autowired
	public MycardImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
}
