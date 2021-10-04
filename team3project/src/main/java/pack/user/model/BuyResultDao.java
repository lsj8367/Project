package pack.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.oldbook.domain.OldBook;
import pack.orderinfo.model.OrderInfoDto;
import pack.oldbook.service.OldBookService;

@Repository
public class BuyResultDao extends SqlSessionDaoSupport {
    private final OldBookService oldBookService;

    public BuyResultDao(SqlSessionFactory sqlSessionFactory, OldBookService oldBookService) {
        setSqlSessionFactory(sqlSessionFactory);
        this.oldBookService = oldBookService;
    }

    private Connection conn = null;

    private PreparedStatement pstmt;
    private ResultSet rs;
    Random random = new Random();
    int count = random.nextInt(99) + 1;

    public OrderInfoDto order(HttpSession session,
        String user_id, String order_person, int order_sum, String radioPaytype, String order_passwd, String address, String ob_no) {
        //sql 주문번호 +
        String sql = "insert into orderinfo(order_no,orderlist_no,order_person,order_id,order_bookno,order_booktype,order_date,"
            + "order_passwd,order_scount, order_paytype, order_state, order_sum, order_address) values(default,?,?,?,?,?,NOW(),?,?,?,?,?,?)";
        OrderInfoDto dto = new OrderInfoDto();
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String id = "root";
            String pw = "123";
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, id, pw);

            Date now = new Date();
            SimpleDateFormat vans = new SimpleDateFormat("yyyyMMdd");
            String wdate = vans.format(now);

            OldBook oldBook = oldBookService.view(ob_no);

            DecimalFormat df = new DecimalFormat("00");

            if (session.getAttribute("id") != null) { //회원
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, wdate + "-" + df.format(count)); // 주문번호 2
                pstmt.setString(2, order_person); //주문자 3
                pstmt.setString(3, user_id); //주문자 4
                pstmt.setInt(4, oldBook.getObNo().intValue()); //책번호 5
                pstmt.setString(5, "2"); //1은 새책 2는 중고책 6
                pstmt.setString(6, " "); //비밀번호 7
                pstmt.setInt(7, 1); //수량 8
                pstmt.setString(8, radioPaytype); //결제수단 9
                pstmt.setString(9, "0"); //배송현황 10
                pstmt.setInt(10, order_sum); //금액 11
                pstmt.setString(11, address); //주소
                pstmt.executeUpdate();

            } else { //비회원
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, wdate + "-" + df.format(count)); // 주문번호
                pstmt.setString(2, order_person); //주문자
                pstmt.setString(3, ""); //주문자
                pstmt.setInt(4, oldBook.getObNo().intValue()); //책번호
                pstmt.setString(5, "1"); //책유형 (급)
                pstmt.setString(6, order_passwd); //비밀번호
                pstmt.setInt(7, 1); //수량
                pstmt.setString(8, radioPaytype); //결제수단
                pstmt.setString(9, "0"); //배송현황
                pstmt.setInt(10, order_sum); //금액
                pstmt.setString(11, address); //주소
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("order err : " + e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return dto;
    }

}
