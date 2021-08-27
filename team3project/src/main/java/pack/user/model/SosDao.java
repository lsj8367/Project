package pack.user.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.model.InqueryDto;

@Repository
public class SosDao extends SqlSessionDaoSupport {

	@Autowired
	public SosDao(SqlSessionFactory factory) {
			setSqlSessionFactory(factory);
	}
	
	public boolean insertInquery(InqueryDto dto) {
		try {
			getSqlSession().insert("insertInquery", dto);
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
