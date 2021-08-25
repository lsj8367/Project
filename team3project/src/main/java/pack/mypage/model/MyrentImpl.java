package pack.mypage.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.model.OldBookDto;
import pack.model.UserDto;

import java.util.List;

@Repository
public class MyrentImpl extends SqlSessionDaoSupport {
	
	@Autowired
	public MyrentImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public List<OldBookDto> rentlistall(String userid) {
		return getSqlSession().selectList("rentlistall", userid);
	}
	
	public OldBookDto getObPrice(int ob_no) {
		return getSqlSession().selectOne("getobp", ob_no);
	}
	
	public boolean deleteRentinf(int rent_no) {
		try {
			getSqlSession().update("delrinf", rent_no);
			return true;
		} catch (Exception e) {
			System.out.println("deleteRentinf error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean upObProcess(int rent_no) {
		try {
			getSqlSession().update("upobprocess", rent_no);
			return true;
		} catch (Exception e) {
			System.out.println("upObProcess error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean delpointuser(UserDto bean) {
		try {
			getSqlSession().update("delpointuser", bean);
			return true;
		} catch (Exception e) {
			System.out.println("delpointuser error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean updateState(int rent_no) {
		try {
			getSqlSession().update("uprent", rent_no);
			return true;
		} catch (Exception e) {
			System.out.println("updateThrow error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
}
