package pack.give.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.common.utils.FileUtils;
import pack.obfile.domain.ObFile;
import pack.obfile.repository.ObFileRepository;
import pack.oldbook.domain.OldBook;
import pack.oldbook.repository.OldBookRepository;

@Service
@RequiredArgsConstructor
public class GiveService {
    private final FileUtils fileUtils;
    private final OldBookRepository oldBookRepository;
    private final ObFileRepository obFileRepository;

    public void insertOldBook(Map<String, Object> map, HttpServletRequest request) throws Exception {
        oldBookRepository.save(OldBook.toEntity(map));
        List<ObFile> fileList = fileUtils.parseInsertFileInfo(map, request);
        for (ObFile obFile : fileList) {
            obFileRepository.save(obFile);
        }
    }
}
