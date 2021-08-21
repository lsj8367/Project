package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BuyImpl extends SqlSessionDaoSupport{
	public BuyImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	public UserDto point(String user_id) {
		return getSqlSession().selectOne("viewpoint", user_id);
	}
	public OrderInfoDto show(String order_person) {
		return getSqlSession().selectOne("view", order_person);
	}
	
	
	
}
