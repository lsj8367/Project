package pack.give.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.domain.entity.OldBook;
import pack.give.dao.GiveDao;
import pack.give.util.FileUtils;
import pack.repository.OldBookRepository;

@Service
public class GiveService {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private GiveDao giveDao;

    @Autowired
    private OldBookRepository oldBookRepository;

    public void insertOldBook(Map<String, Object> map, HttpServletRequest request) throws Exception {
        oldBookRepository.save(OldBook.toEntity(map));
        List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
        for (int i = 0; i < list.size(); i++) {
            giveDao.insertFile(list.get(i));
        }
    }
}
