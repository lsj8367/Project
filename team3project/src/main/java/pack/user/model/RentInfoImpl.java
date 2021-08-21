package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.RentInfoBean;

@Repository
public class RentInfoImpl extends SqlSessionDaoSupport {
	public RentInfoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public boolean rentOldBook(RentInfoBean bean) {
		try {
			getSqlSession().insert("rentOldBook", bean);
			return true;
		}catch (Exception e) {
			System.out.println("rentOldBook" + e);
			return false;
		}
	}
	
	public RentInfoDto getRentInfo(String rent_id) {
		return getSqlSession().selectOne("getRentInfo",rent_id);
	}
}
