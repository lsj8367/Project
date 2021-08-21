package pack.user.model
		;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.OldBookDto;

import java.util.List;

@Repository
public class CartDao extends SqlSessionDaoSupport {
	public CartDao(SqlSessionFactory factory) {
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
