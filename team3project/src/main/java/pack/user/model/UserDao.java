package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao extends SqlSessionDaoSupport {
	public UserDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	// 로그인
	public UserDto selectUser(String user_id) {
		return getSqlSession().selectOne("selectUser", user_id);
	}
	
	// 회원가입
	
	public boolean insertUser(UserDto userDto) {
		try {
			getSqlSession().insert("insertUser", userDto);
			return true;
		}catch (Exception e) {
			System.out.println("insertUser" + e);
			return false;
		}
	}
	
	public int checkUserId(String user_id) {
		return getSqlSession().selectOne("checkUserId", user_id);
	}

	public boolean usePoint(UserDto bean) {
		try {
			getSqlSession().update("usePoint", bean);
			return true;
		}catch (Exception e) {
			System.out.println("usePoint" + e);
			return false;
		}
	}
	
	public boolean minusRentPoint(String user_id) {
		try {
			getSqlSession().update("minusRentPoint", user_id);
			return true;
		}catch (Exception e) {
			System.out.println("minusRentPoint" + e);
			return false;
		}
	}
}
