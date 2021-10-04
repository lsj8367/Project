package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.orderinfo.model.OrderInfoDto;

@Repository
public class OrderInfoDao extends SqlSessionDaoSupport {

	public OrderInfoDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
		
	public boolean buyNewBookUser(OrderInfoDto orderInfoDto) {
		try {
			getSqlSession().insert("buyNewBookUser", orderInfoDto);
			return true;
		} catch (Exception e) {
			System.out.println("buyNewBookUser error : " + e); 		
			return false;
		}
	}
	public boolean buyNewBookUnuser(OrderInfoDto orderInfoDto) {
		try {
			getSqlSession().insert("buyNewBookUnuser", orderInfoDto);
			return true;
		} catch (Exception e) {
			System.out.println("buyNewBookUnuser error : " + e); 		
			return false;
		}
	}
	
	public OrderInfoDto unmemberOrder(OrderInfoDto orderInfoDto) {
		return getSqlSession().selectOne("unmemberOrder", orderInfoDto);
	}
	
	public OrderInfoDto getOrderbyPass(String order_passwd) {
		return getSqlSession().selectOne("getOrderbyPass", order_passwd);
	}
}
