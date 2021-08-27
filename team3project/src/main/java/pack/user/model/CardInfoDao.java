package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.CardInfoDto;

@Repository
public class CardInfoDao extends SqlSessionDaoSupport {
	public CardInfoDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	// 회원가입할 때 카드 등록
	public boolean insertCard(CardInfoDto dto) {
		try {
			getSqlSession().insert("insertCard", dto);
			return true;
		}catch (Exception e) {
			System.out.println("insertCard" + e);
			return false;
		}
	}
	
	//결제 창에서 회원일 시 등록된 카드 가져오기
	public CardInfoDto selectCard(String card_ownerid) {
		return getSqlSession().selectOne("selectCard", card_ownerid);
	}
}
