package pack.user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.oldbook.domain.OldBook;
import pack.common.enums.Grade;
import pack.oldbook.repository.OldBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OldBookService {

    private final OldBookRepository oldBookRepository;

    public OldBook bookInfo(String book_no) {
        return oldBookRepository.findByObStateAndObNo("1", Long.valueOf(book_no));
    }

    public OldBook rentalInfo(String book_no) {
        return oldBookRepository.findByObStateInAndObNo(List.of(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade()), Long.valueOf(book_no));
    }

    public boolean updateReadCnt(int obNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObReadcnt(oldBook.getObReadcnt() + 1);
        });

        return true;
    }

    public OldBook view(String ob_no) {
        return oldBookRepository.findById(Long.valueOf(ob_no)).orElseThrow(() -> {
                throw new RuntimeException("해당하는 중고책 없음");
            });
    }

    public void updateOldBook(int obNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObDonor("10");
        });
    }

    public Map<String, Object> selectGiveList(String ob_userid) {
        return oldBookRepository.selectGiveList(ob_userid);
    }

    public void updateRentOldBook(String rent_no) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(Long.valueOf(rent_no));
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObDonor("1");
        });
    }

}
