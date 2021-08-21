package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OldBookListImpl extends SqlSessionDaoSupport {
	
	public OldBookListImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	public List<OldBookDto> selectGenre(String ob_genre) {
		return getSqlSession().selectList("oldGenre", ob_genre);
	}
	
	public List<OldBookDto> selectGenre2(String ob_genre) {
		return getSqlSession().selectList("oldGenre2", ob_genre);
	}
	
	public List<OldBookDto> rentmain() {
		return getSqlSession().selectList("oldRandom");
	}
	
	public List<OldBookDto> rentmain2() {
		return getSqlSession().selectList("oldlow");
	}
	
	public List<OldBookDto> selectHighAll() {
		return getSqlSession().selectList("oldHigh");
	}
	public List<OldBookDto> selectLowAll() {
		return getSqlSession().selectList("oldLow");
	}
	
	public OldBookDto bestOne() {
		return getSqlSession().selectOne("selectBestRentBook");
	}
	
	public UserDto bestRead() {
		return getSqlSession().selectOne("selectBestRead");
	}
	
}
