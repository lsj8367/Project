package pack.give.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.common.utils.FileUtils;
import pack.give.dao.GiveDao;
import pack.oldbook.domain.OldBook;
import pack.oldbook.repository.OldBookRepository;

@Service
@RequiredArgsConstructor
public class GiveService {
    private final FileUtils fileUtils;
    private final GiveDao giveDao;
    private final OldBookRepository oldBookRepository;

    public void insertOldBook(Map<String, Object> map, HttpServletRequest request) throws Exception {
        oldBookRepository.save(OldBook.toEntity(map));
        List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
        for (Map<String, Object> stringObjectMap : list) {
            giveDao.insertFile(stringObjectMap);
        }
    }
}
