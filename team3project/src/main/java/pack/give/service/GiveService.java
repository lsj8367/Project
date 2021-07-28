package pack.give.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import pack.give.dao.GiveDao;
import pack.give.util.FileUtils;
import pack.model.OldBookDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class GiveService extends SqlSessionDaoSupport{

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Resource(name = "giveDao")
	private GiveDao giveDao;

	/*
	 *
	 * private static String namespace = "pack.give.dao.GiveDao";
	 */
	public GiveService(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
		
	}

	public void insertOldBook(Map<String, Object> map, HttpServletRequest request) throws Exception {
		giveDao.insertOldBook(map);
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for (int i = 0; i < list.size(); i++) {
			giveDao.insertFile(list.get(i));
		}
	}
	
	public OldBookDto getGiveList(String ob_userid) throws Exception{
		return (OldBookDto) giveDao.getGiveList(ob_userid);
	}
	
}
