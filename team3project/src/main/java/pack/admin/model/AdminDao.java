package pack.admin.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.model.AdminDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.RentInfoDto;
import pack.model.ReviewDto;
import pack.model.UserDto;

@Repository
public class AdminDao extends SqlSessionDaoSupport {

    public AdminDao(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public List<NewBookDto> selectBookDataAll() {
        return getSqlSession().selectList("selectBookDataAll");
    }

    public List<UserDto> selectUserAll() {
        return getSqlSession().selectList("selectUserAll");
    }

    public List<OldBookDto> selectSellObAll() {
        return getSqlSession().selectList("selectSellObAll");
    }

    public List<OldBookDto> selectStandbyAll() {
        return getSqlSession().selectList("selectStandbyAll");
    }

    public boolean upobstate(OldBookDto dto) {
        try {
            getSqlSession().update("upobstate", dto);
            return true;
        } catch (Exception e) {
            System.out.println("updateState error : " + e);    //개발자를 위한 내용
            return false;
        }
    }

    public List<OldBookDto> selectRentBookAll() {
        return getSqlSession().selectList("selectRentBookAll");
    }

    public List<OldBookDto> selectReuseAll() {
        return getSqlSession().selectList("selectReuseAll");
    }

    public List<OrderInfoDto> selectnbOrderAll() {
        return getSqlSession().selectList("selectnbOrderAll");
    }

    public List<OrderInfoDto> selectobOrderAll() {
        return getSqlSession().selectList("selectobOrderAll");
    }

    public List<RentInfoDto> selectRentAll() {
        return getSqlSession().selectList("selectRentAll");
    }

    public List<UserDto> selectUserPointAll() {
        return getSqlSession().selectList("selectUserPointAll");
    }

    public List<OrderInfoDto> selectNobankAll() {
        return getSqlSession().selectList("selectNobankAll");
    }

    public boolean uporderstate(OrderInfoDto orderInfoDto) {
        try {
            getSqlSession().update("uporderstate", orderInfoDto);
            return true;
        } catch (Exception e) {
            System.out.println("updateOrderState error : " + e);    //개발자를 위한 내용
            return false;
        }
    }

    public List<OrderInfoDto> selectorderAll() {
        return getSqlSession().selectList("selectorderAll");
    }

    public boolean obthrow(int ob_no) {
        try {
            getSqlSession().update("obthrow", ob_no);
            return true;
        } catch (Exception e) {
            System.out.println("updateThrow error : " + e);    //개발자를 위한 내용
            return false;
        }
    }

    public List<RentInfoDto> selectdelayAll() {
        return getSqlSession().selectList("selectdelayAll");
    }

    public boolean upuser(UserDto bean) {
        try {
            getSqlSession().update("upuser", bean);
            return true;
        } catch (Exception e) {
            System.out.println("updateUser error : " + e);    //포인트 차감, 상태 업데이트
            return false;
        }
    }

    public boolean removeOb(int ob_no) {
        try {
            getSqlSession().delete("rmoldbook", ob_no);
            getSqlSession().delete("rmrentinfo");
            return true;
        } catch (Exception e) {
            System.out.println("removeOb error : " + e);    //장기연체 도서 삭제
            return false;
        }
    }

    public List<String> selectdelayid() {
        return getSqlSession().selectList("selectdelayid");
    }

    public boolean updcount(String user_id) {
        try {
            getSqlSession().update("updcount", user_id);
            return true;
        } catch (Exception e) {
            System.out.println("updateDcount error : " + e);    //연체 횟수
            return false;
        }
    }

    public List<UserDto> selectdelay() {
        return getSqlSession().selectList("selectdelay");
    }

    public boolean uppenalty(UserDto bean) {
        try {
            getSqlSession().update("uppenalty", bean);
            return true;
        } catch (Exception e) {
            System.out.println("updatePenalty error : " + e);    //패널티 업데이트
            return false;
        }
    }

    public List<UserDto> selectrefusecount() {
        return getSqlSession().selectList("selectrefusecount");
    }

    public List<UserDto> selectdeluser() {
        return getSqlSession().selectList("selectdeluser");
    }

    public List<UserDto> selectuserpcheck() {
        return getSqlSession().selectList("selectuserpcheck");
    }

    public boolean updeluser(String user_id) {
        try {
            getSqlSession().update("updeluser", user_id);
            return true;
        } catch (Exception e) {
            System.out.println("updateDelUser error : " + e);    //연체 횟수
            return false;
        }
    }

    public List<OrderInfoDto> selectdelaydeposit() {
        return getSqlSession().selectList("selectdelaydeposit");
    }

    public boolean rmorder(String orderlist_no) {
        try {
            getSqlSession().delete("rmorder", orderlist_no);
            return true;
        } catch (Exception e) {
            System.out.println("delOrder error : " + e);    //미납 주문 삭제
            return false;
        }
    }

    public List<ReviewDto> selectreviewAll() {
        return getSqlSession().selectList("selectreviewAll");
    }

    public List<OrderInfoDto> obprofit() {
        return getSqlSession().selectList("obprofit");
    }

    public List<OrderInfoDto> nbprofit() {
        return getSqlSession().selectList("nbprofit");
    }

    public List<OrderInfoDto> profit() {
        return getSqlSession().selectList("profit");
    }

    public OrderInfoDto obprofitmonth() {
        return getSqlSession().selectOne("obprofitmonth");
    }

    public OrderInfoDto nbprofitmonth() {
        return getSqlSession().selectOne("nbprofitmonth");
    }

    public OrderInfoDto profitMonth() {
        return getSqlSession().selectOne("profitmonth");
    }

    public List<ReviewDto> mbEstReview(String sql) {
        return getSqlSession().selectList("mbestreview", sql);
    }

    public List<ReviewDto> mbReviewMonth() {
        return getSqlSession().selectList("mbreviewmonth");
    }

    public boolean upUserPoint(UserDto bean) {
        try {
            getSqlSession().insert("upuserpoint", bean);
            return true;
        } catch (Exception e) {
            System.out.println("upUserPoint error : " + e);    //개발자를 위한 내용
            return false;
        }
    }

    public List<NewBookDto> mbSellerMonth() {
        return getSqlSession().selectList("mbsellermonth");
    }

    public List<NewBookDto> mbSellerCmonth() {
        return getSqlSession().selectList("mbsellercmonth");
    }

    public List<NewBookDto> mbestSeller(String sql) {
        return getSqlSession().selectList("mbestseller", sql);
    }

    public List<RentInfoDto> mbRentMonth() {
        return getSqlSession().selectList("mbrentmonth");
    }

    public List<RentInfoDto> mbRentCmonth() {
        return getSqlSession().selectList("mbrentcmonth");
    }

    public List<RentInfoDto> mbEstRent(String sql) {
        return getSqlSession().selectList("mbestrent", sql);
    }

    public List<RentInfoDto> rentKing() {
        return getSqlSession().selectList("rentking");
    }

    public String currentMonth() {
        return getSqlSession().selectOne("currentmonth");
    }

    public List<OrderInfoDto> buyKing() {
        return getSqlSession().selectList("buyking");
    }

    public List<OldBookDto> getMostRentBook() {
        return getSqlSession().selectList("getmostrentbook");
    }

    public boolean adminInsert(AdminDto adminDto) {
        try {
            getSqlSession().insert("admininsert", adminDto);
            return true;
        } catch (Exception e) {
            System.out.println("insertAdmin error : " + e);    //개발자를 위한 내용
            return false;
        }
    }

}
