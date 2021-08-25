package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.NewBookDto;

import java.util.List;

@Repository
public class NewBookDao extends SqlSessionDaoSupport {
	
	public NewBookDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
		
	public List<NewBookDto> getBookAll() {
		return getSqlSession().selectList("selectBookAll");
	}

	public List<NewBookDto> getGenre(String nb_genre) {
		return getSqlSession().selectList("selectGenre", nb_genre);
	}
	
	public List<NewBookDto> getBest() {
		return getSqlSession().selectList("selectBest30");
	}
	
	public List<NewBookDto> getNew() {
		return getSqlSession().selectList("selectNew");
	}
	
	// 책 세부내용 newbook.jsp
	public NewBookDto selectNewbook(int nb_no) {
		return getSqlSession().selectOne("selectNewbook", nb_no);
	}
	
	public List<NewBookDto> selectAuthorList(String nb_authour) {
		return getSqlSession().selectList("selectAuthorList", nb_authour);
	}
	
	//새책 조회수 올리기
	public void plusReadCnt(int nb_no) {
		try {
			getSqlSession().update("plusReadCnt", nb_no);
		}catch (Exception e) {
			System.out.println("plusReadCnt" + e);
		}
	}
	
}
