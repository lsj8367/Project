package pack.mypage.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyorderImpl extends SqlSessionDaoSupport {

    @Autowired
    public MyorderImpl(SqlSessionFactory factory) {
        setSqlSessionFactory(factory);
    }

    public boolean deletemyorder(int order_no) {
        try {
            getSqlSession().delete("delmyord", order_no);
            return true;
        } catch (Exception e) {
            System.out.println("deletemyorder error : " + e);    //개발자를 위한 내용
            return false;
        }
    }

}
