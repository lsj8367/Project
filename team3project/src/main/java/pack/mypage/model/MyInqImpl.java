package pack.mypage.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.InqueryDto;

import java.util.List;

@Repository
public class MyInqImpl extends SqlSessionDaoSupport {

	public MyInqImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public List<InqueryDto> inqListAll(String user_id) {
		
		return getSqlSession().selectList("selectInqList", user_id);
	}
}
