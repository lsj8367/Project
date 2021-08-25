package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.model.NewBookDto;

import java.util.List;

@Repository
public class NewBookListDao extends SqlSessionDaoSupport {
	
	@Autowired
	public NewBookListDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	public List<NewBookDto> selectReadTop3() {
		return getSqlSession().selectList("selectReadTop3");
	}
	
	public List<NewBookDto> selectRandom10() {
		return getSqlSession().selectList("selectRandom10");
	}
	
	public NewBookDto selectBest() {
		return getSqlSession().selectOne("selectBest");
	}
}
