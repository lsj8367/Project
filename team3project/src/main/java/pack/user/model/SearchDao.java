package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import pack.model.NewBookDto;

@Repository
public class SearchDao extends SqlSessionDaoSupport {
   public SearchDao(SqlSessionFactory factory) {
      setSqlSessionFactory(factory);
   }

   public List<NewBookDto> getDataAll(String search) {
      List<NewBookDto> list = new ArrayList<NewBookDto>();
      NewBookDto dto = new NewBookDto();
      dto.setNb_name(search);
      getSqlSession().selectList("getDataNewAllExist", dto);

      return list;
   }
   
}