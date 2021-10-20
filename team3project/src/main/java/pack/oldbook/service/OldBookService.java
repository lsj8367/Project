package pack.oldbook.service;

import static pack.common.enums.Grade.FIRST_GRADE;
import static pack.common.enums.Grade.SECOND_GRADE;
import static pack.common.enums.Grade.THIRD_GRADE;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.oldbook.domain.OldBook;
import pack.common.enums.Grade;
import pack.oldbook.repository.OldBookRepository;
import pack.user.domain.OldSearch;

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

    public List<OldBook> getDataAllExist(OldSearch oldSearch) {
        return oldBookRepository.findAllByObNameContainsOrderByObNoDesc(oldSearch.getSearch());
    }

    public boolean updateReadCnt(int obNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObReadcnt(oldBook.getObReadcnt() + 1);
        });

        return true;
    }

    public List<OldBook> genreForFirstGrade(String obGenre) {
        return oldBookRepository.findAllByObStateAndObDonorNotAndObGenreContains(FIRST_GRADE.getGrade(), "10", obGenre);
    }

    public List<OldBook> genreForAnotherGrade(String ob_genre) {
        return oldBookRepository.findAllByObStateInAndObGenreContains(List.of(SECOND_GRADE.getGrade(), THIRD_GRADE.getGrade()), ob_genre);
    }

    public List<OldBook> randomFirstGrade() {
        return oldBookRepository.oldRandom();
    }

    public List<OldBook> oldLowLimit2() {
        return oldBookRepository.oldLowLimit2();
    }

    public OldBook selectBestRentBook() {
        return oldBookRepository.selectBestRentBook();
    }

    public List<OldBook> oldHigh() {
        return oldBookRepository.findAllByObStateAndObDonorNotOrderByObNoDesc(FIRST_GRADE.getGrade(), "10");
    }

    public List<OldBook> oldLow() {
        return oldBookRepository.findAllByObStateOrObStateOrderByObNoDesc(SECOND_GRADE.getGrade(), THIRD_GRADE.getGrade());
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

    public List<OldBook> selectOldBookTop3(String userId) {
        return oldBookRepository.findTop3ByObDonorOrderByObNameDesc(userId);
    }

}
