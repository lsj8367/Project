package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import pack.rentinfo.model.RentInfoDto;

@Repository
public class RentInfoDao extends SqlSessionDaoSupport {

    public RentInfoDao(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public RentInfoDto getRentInfo(String rent_id) {
        return getSqlSession().selectOne("getRentInfo", rent_id);
    }

}
