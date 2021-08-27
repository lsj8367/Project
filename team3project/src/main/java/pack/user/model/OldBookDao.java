package pack.user.model;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.model.OldBookDto;

@Repository
public class OldBookDao extends SqlSessionDaoSupport {

	@Autowired
	public OldBookDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public OldBookDto bookInfo(String book_no) {
		return getSqlSession().selectOne("oldbookinfo", book_no);
	}
	//oldbookinfo2
	public OldBookDto rentalInfo(String book_no) {
		return getSqlSession().selectOne("oldbookinfo2", book_no);
	}
	
	public boolean readcnt(int readcnt) {
		try {
			getSqlSession().update("readcntUpdate", readcnt);
			return true;
		} catch (Exception e) {
			System.out.println("readcnt error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public OldBookDto view(String book_no) {
		return getSqlSession().selectOne("viewoldbook", book_no);
	}
	
	public boolean updateRentOldBook(String book_no) {
		try {
			getSqlSession().update("updateRentOldBook", book_no);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean update(int ob_no) {
		try {
			getSqlSession().update("updateOldbook", ob_no);
			return true;
		} catch (Exception e) {
			System.out.println("update error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
}
