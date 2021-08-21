package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartImpl extends SqlSessionDaoSupport {
	public CartImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public List<OldBookDto> cartlist(String user_id) {
		return getSqlSession().selectList("cartList", user_id);
	}
	
	public boolean delete(int order_no) {
		int result = getSqlSession().delete("deleteData", order_no);
		if(result > 0) {
			return true;
		}else {
			return false;				
				}
		}
	
	
}
