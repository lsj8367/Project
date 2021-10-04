package pack.user.model;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.orderinfo.model.OrderInfoDto;

@Repository
public class UnmemberDao extends SqlSessionDaoSupport {
	public UnmemberDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public OrderInfoDto search(OrderInfoDto orderInfoDto) {
		return getSqlSession().selectOne("unmembercheck", orderInfoDto);
	}
	
}
