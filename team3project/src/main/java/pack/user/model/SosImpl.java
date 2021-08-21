package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.controller.InqueryBean;

import java.util.List;

@Repository
public class SosImpl extends SqlSessionDaoSupport {

	@Autowired
	public SosImpl(SqlSessionFactory factory) {
			setSqlSessionFactory(factory);
	}
	
	public boolean insertInquery(InqueryBean bean) {
		try {
			getSqlSession().insert("insertInquery", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertInquery err : " +e);
			return false;
		}
	}
	public List<InqueryDto> inqlist(String inq_id) {
		
		return getSqlSession().selectList("selectInqList", inq_id);
	}
	
}
