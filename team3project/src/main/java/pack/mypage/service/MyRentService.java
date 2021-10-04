package pack.mypage.service;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.oldbook.domain.OldBook;
import pack.oldbook.repository.OldBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MyRentService {

    private final OldBookRepository oldBookRepository;

    public void upObProcess(Long rentNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(rentNo);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObDonor("0");
        });
    }

    public OldBook getObPrice(long rent_no) {
        return oldBookRepository.findById(rent_no).orElseThrow(() -> {
                throw new RuntimeException("해당하는 사람 없음");
            });
    }

}
