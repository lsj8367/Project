package pack.admin.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.newbook.model.NewBookDto;
import pack.orderinfo.model.OrderInfoDto;
import pack.rentinfo.model.RentInfoDto;
import pack.review.model.ReviewDto;

@Repository
public class AdminDao extends SqlSessionDaoSupport {

    public AdminDao(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public List<RentInfoDto> selectRentAll() {
        return getSqlSession().selectList("selectRentAll");
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

    public List<RentInfoDto> selectdelayAll() {
        return getSqlSession().selectList("selectdelayAll");
    }

    public List<String> selectdelayid() {
        return getSqlSession().selectList("selectdelayid");
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
