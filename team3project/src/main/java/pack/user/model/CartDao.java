package pack.user.model
		;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao extends SqlSessionDaoSupport {
	public CartDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	public boolean delete(int order_no) {
		int result = getSqlSession().delete("deleteData", order_no);
		return result > 0;
	}
}
