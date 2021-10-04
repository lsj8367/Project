package pack.user.service;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.oldbook.domain.OldBook;
import pack.oldbook.repository.OldBookRepository;
import pack.user.domain.OldSearch;

@Service
@Transactional
@RequiredArgsConstructor
public class OldSearchService {
    private final OldBookRepository oldBookRepository;

    public List<OldBook> getDataAllExist(OldSearch oldSearch) {
        return oldBookRepository.findAllByObNameContainsOrderByObNoDesc(oldSearch.getSearch());
    }

}
