package pack.admin.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.AdminDto;
import pack.model.InqueryDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.RentInfoDto;
import pack.model.ReviewDto;
import pack.model.UserDto;

@Repository
public class AdminDao extends SqlSessionDaoSupport{
	
	public AdminDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	public pack.model.AdminDto getAdminLoginInfo(String admin_id) {
		return getSqlSession().selectOne("selectAdminData", admin_id);
	}

	public boolean insertBookData(NewBookDto newBookDto) {
		try {
			getSqlSession().insert("insertBookData", newBookDto);
			return true;
		} catch (Exception e) {
			System.out.println("insertBookData error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<NewBookDto> getNewBook() {
		return getSqlSession().selectList("selectBookDataAll");
	}
	
	public List<UserDto> getUser() {
		return getSqlSession().selectList("selectUserAll");
	}
	
	public List<OldBookDto> getSellOb() {
		return getSqlSession().selectList("selectSellObAll");
	}
	
	public List<OldBookDto> getStandby() {
		return getSqlSession().selectList("selectStandbyAll");
	}
	
	public boolean updateState(OldBookDto dto) {
		try {
			getSqlSession().update("upobstate", dto);
			return true;
		} catch (Exception e) {
			System.out.println("updateState error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<OldBookDto> getRBook() {
		return getSqlSession().selectList("selectRentBookAll");
	}
	
	public List<OldBookDto> getReuse() {
		return getSqlSession().selectList("selectReuseAll");
	}
	
	public List<OrderInfoDto> getnbOrderData() {
		return getSqlSession().selectList("selectnbOrderAll");
	}
	
	public List<OrderInfoDto> getobOrderData() {
		return getSqlSession().selectList("selectobOrderAll");
	}
	
	public List<RentInfoDto> getRentList() {
		return getSqlSession().selectList("selectRentAll");
	}
	
	public List<UserDto> getUserPoint() {
		return getSqlSession().selectList("selectUserPointAll");
	}
	
	public List<OrderInfoDto> getNobank() {
		return getSqlSession().selectList("selectNobankAll");
	}
	
	public boolean updateOrderState(OrderInfoDto orderInfoDto) {
		try {
			getSqlSession().update("uporderstate", orderInfoDto);
			return true;
		} catch (Exception e) {
			System.out.println("updateOrderState error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<OrderInfoDto> getOrderData() {
		return getSqlSession().selectList("selectorderAll");
	}
	
	public boolean updateThrow(int ob_no) {
		try {
			getSqlSession().update("obthrow", ob_no);
			return true;
		} catch (Exception e) {
			System.out.println("updateThrow error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<RentInfoDto> getDelayData() {
		return getSqlSession().selectList("selectdelayAll");
	}
	
	public boolean updateUser(UserDto bean) {
		try {
			getSqlSession().update("upuser", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updateUser error : " + e); 		//포인트 차감, 상태 업데이트
			return false;
		}
	}
	
	public boolean removeOb(int ob_no) {
		try {
			getSqlSession().delete("rmoldbook", ob_no);
			getSqlSession().delete("rmrentinfo");
			return true;
		} catch (Exception e) {
			System.out.println("removeOb error : " + e); 		//장기연체 도서 삭제
			return false;
		}
	}
	
	public List<String> getDelayId() {
		return getSqlSession().selectList("selectdelayid");
	}
	
	public boolean updateDcount(String user_id) {
		try {
			getSqlSession().update("updcount", user_id);
			return true;
		} catch (Exception e) {
			System.out.println("updateDcount error : " + e); 		//연체 횟수
			return false;
		}
	}
	
	public List<UserDto> getDelay() {
		return getSqlSession().selectList("selectdelay");
	}
	
	public boolean updatePenalty(UserDto bean) {
		try {
			getSqlSession().update("uppenalty", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updatePenalty error : " + e); 		//패널티 업데이트
			return false;
		}
	}
	
	public List<UserDto> getRefuse() {
		return getSqlSession().selectList("selectrefusecount");
	}
	
	public List<UserDto> getUserdel() {
		return getSqlSession().selectList("selectdeluser");
	}
	
	public List<UserDto> getUsercheck() {
		return getSqlSession().selectList("selectuserpcheck");
	}
	
	public boolean updateDelUser(String user_id) {
		try {
			getSqlSession().update("updeluser", user_id);
			return true;
		} catch (Exception e) {
			System.out.println("updateDelUser error : " + e); 		//연체 횟수
			return false;
		}
	}
	
	public List<OrderInfoDto> getDelayDeposit() {
		return getSqlSession().selectList("selectdelaydeposit");
	}
	
	public boolean delOrder(String orderlist_no) {
		try {
			getSqlSession().delete("rmorder", orderlist_no);
			return true;
		} catch (Exception e) {
			System.out.println("delOrder error : " + e); 		//미납 주문 삭제
			return false;
		}
	}

	public List<InqueryDto> getinqlist() {
		return getSqlSession().selectList("selectinqAll");
	}
	
	public List<ReviewDto> getreviewAll() {
		return getSqlSession().selectList("selectreviewAll");
	}
	
	public List<OrderInfoDto> getObProfit() {
		return getSqlSession().selectList("obprofit");
	}

	public List<OrderInfoDto> getNbProfit() {
		return getSqlSession().selectList("nbprofit");
	}
	
	public List<OrderInfoDto> getProfit() {
		return getSqlSession().selectList("profit");
	}
	
	public OrderInfoDto getObProfitmonth() {
		return getSqlSession().selectOne("obprofitmonth");
	}
	public OrderInfoDto getNbProfitmonth() {
		return getSqlSession().selectOne("nbprofitmonth");
	}
	
	public OrderInfoDto getProfitmonth() {
		return getSqlSession().selectOne("profitmonth");
	}
	
	public List<pack.model.AdminDto> getAdminyet() {
		return getSqlSession().selectList("adminyetAll");
	}
	
	public boolean delAdmin(String admin_id) {
		try {
			getSqlSession().insert("deladmin", admin_id);
			return true;
		} catch (Exception e) {
			System.out.println("delAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean upAdmin(AdminDto adminDto) {
		try {
			getSqlSession().insert("upadmin", adminDto);
			return true;
		} catch (Exception e) {
			System.out.println("upAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<AdminDto> getAdminInfo() {
		return getSqlSession().selectList("getadminAll");
	}
	
	public boolean upAdminJik(AdminDto adminDto) {
		try {
			getSqlSession().insert("upadminjik", adminDto);
			return true;
		} catch (Exception e) {
			System.out.println("upAdminJik error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<ReviewDto> getBestReview() {
		return getSqlSession().selectList("mbrmonth");
	}
	
	public List<ReviewDto> getBestReviewmonth(String sql) {
		return getSqlSession().selectList("mbestreview",sql);
	}
	
	public List<ReviewDto> getRmonth() {
		return getSqlSession().selectList("mbreviewmonth");
	}
	
	public boolean upUserPoint(UserDto bean) {
		try {
			getSqlSession().insert("upuserpoint", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upUserPoint error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<NewBookDto> getOmonth() {
		return getSqlSession().selectList("mbsellermonth");
	}
	
	public List<NewBookDto> getOcmonth() {
		return getSqlSession().selectList("mbsellercmonth");
	}
	
	public List<NewBookDto> getBestSellermonth(String sql) {
		return getSqlSession().selectList("mbestseller",sql);
	}
	
	public boolean upNbStock(NewBookDto newBookDto) {
		try {
			getSqlSession().insert("upnbstock", newBookDto);
			return true;
		} catch (Exception e) {
			System.out.println("upNbStock error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<RentInfoDto> getRentmonth() {
		return getSqlSession().selectList("mbrentmonth");
	}
	
	public List<RentInfoDto> getRentcmonth() {
		return getSqlSession().selectList("mbrentcmonth");
	}
	
	public List<RentInfoDto> getBestRentmonth(String sql) {
		return getSqlSession().selectList("mbestrent",sql);
	}
	
	public List<RentInfoDto> getRentKing() {
		return getSqlSession().selectList("rentking");
	}
	
	public String getMonth() {
		return getSqlSession().selectOne("currentmonth");
	}
	
	public List<OrderInfoDto> getBuyKing() {
		return getSqlSession().selectList("buyking");
	}
	
	public InqueryDto getMaxNum() {
		return getSqlSession().selectOne("getMaxNum");
	}
	
	public InqueryDto getInqData(int inq_no) {
		return getSqlSession().selectOne("selectInqPart", inq_no);
	}
	
	public boolean upOnum(InqueryDto inqueryDto) {
		try {
			getSqlSession().update("updateOnum", inqueryDto);
			return true;
		} catch (Exception e) {
			System.out.println("upOnum error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean insInqReply(InqueryDto inqueryDto) {
		try {
			getSqlSession().insert("insertinqReply", inqueryDto);
			return true;
		} catch (Exception e) {
			System.out.println("insInqReply error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean RollbackStock(NewBookDto newBookDto) {
		try {
			getSqlSession().insert("rollbackstock", newBookDto);
			return true;
		} catch (Exception e) {
			System.out.println("RollbackStock error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public List<NewBookDto> theMostSellBook() {
		return getSqlSession().selectList("getmostsellbook");
	}
	
	public List<OldBookDto> theMostRentBook() {
		return getSqlSession().selectList("getmostrentbook");
	}
	
	public String IdCheck(String admin_id) {
		return getSqlSession().selectOne("adminidcheck");
	}
	
	public boolean insertAdmin(AdminDto adminDto) {
		try {
			getSqlSession().insert("admininsert", adminDto);
			return true;
		} catch (Exception e) {
			System.out.println("insertAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	public boolean updateAdmin(AdminDto adminDto) {
		try {
			getSqlSession().update("updateadmininfo", adminDto);
			return true;
		} catch (Exception e) {
			System.out.println("updateAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
}
