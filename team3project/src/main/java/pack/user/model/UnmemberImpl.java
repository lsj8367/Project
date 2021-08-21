package pack.model;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.OrderInfoBean;

@Repository
public class UnmemberImpl extends SqlSessionDaoSupport {
	public UnmemberImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public OrderInfoDto search(OrderInfoBean bean) {
		return getSqlSession().selectOne("unmembercheck", bean);
	}
	
}
