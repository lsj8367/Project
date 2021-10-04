package pack.mypage.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.inquery.model.InqueryDto;
import pack.oldbook.model.OldBookDto;
import pack.orderinfo.model.OrderInfoDto;

@Repository
public class MypageImpl extends SqlSessionDaoSupport {

    @Autowired
    public MypageImpl(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public List<OrderInfoDto> orderlist(String id) {
        return getSqlSession().selectList("order3list", id);
    }

    public List<OldBookDto> rentlist(String id) {
        return getSqlSession().selectList("rent3list", id);
    }

    public List<InqueryDto> inqlist(String id) {
        return getSqlSession().selectList("inq3list", id);
    }

}
