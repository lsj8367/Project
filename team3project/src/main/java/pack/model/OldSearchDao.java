package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.user.domain.OldSearch;

import java.util.List;

@Repository
public class OldSearchDao extends SqlSessionDaoSupport {

	public OldSearchDao(SqlSessionFactory sqlSessionFactory) {
		setSqlSessionFactory(sqlSessionFactory);
	}

	public List<OldBookDto> getDataAll(OldSearch oldSearch) {
		List<OldBookDto> list;
		OldBookDto oldBookDto = new OldBookDto();
		oldBookDto.setOb_name(oldSearch.getSearch());
		list = getSqlSession().selectList("getDataAllExist", oldBookDto);
		return list;
	}
}
