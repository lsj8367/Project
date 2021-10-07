package pack.faqboard.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.faqboard.domain.FaqBoard;
import pack.faqboard.repository.FaqBoardRepository;

@Service
@RequiredArgsConstructor
public class FaqBoardService {

    private final FaqBoardRepository faqBoardRepository;

    @Transactional
    public void create(FaqBoard faqBoard) {
        faqBoardRepository.save(faqBoard);
    }

}
