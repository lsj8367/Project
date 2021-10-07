package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.orderinfo.model.OrderInfoDto;

@Repository
public class BuyDao extends SqlSessionDaoSupport {

    public BuyDao(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public OrderInfoDto show(String order_person) {
        return getSqlSession().selectOne("view", order_person);
    }

}
