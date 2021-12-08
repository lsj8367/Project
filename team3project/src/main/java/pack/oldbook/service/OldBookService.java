package pack.oldbook.service;

import static pack.common.enums.Grade.FIRST_GRADE;
import static pack.common.enums.Grade.SECOND_GRADE;
import static pack.common.enums.Grade.THIRD_GRADE;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.oldbook.domain.OldBook;
import pack.common.enums.Grade;
import pack.oldbook.repository.OldBookRepository;
import pack.rentinfo.model.RentInfoDto;
import pack.user.domain.OldSearch;

@Service
@Transactional
@RequiredArgsConstructor
public class OldBookService {

    private final OldBookRepository oldBookRepository;

    public OldBook bookInfo(String bookNo) {
        return oldBookRepository.findByObStateAndObNo("1", Long.valueOf(bookNo));
    }

    public OldBook rentalInfo(String bookNo) {
        return oldBookRepository.findByObStateInAndObNo(List.of(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade()), Long.valueOf(bookNo));
    }

    public List<OldBook> getDataAllExist(OldSearch oldSearch) {
        return oldBookRepository.findAllByObNameContainsOrderByObNoDesc(oldSearch.getSearch());
    }

    public boolean updateReadCnt(int obNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBook -> oldBook.setObReadcnt(oldBook.getObReadcnt() + 1));

        return true;
    }

    public List<OldBook> genreForFirstGrade(String obGenre) {
        return oldBookRepository.findAllByObStateAndObDonorNotAndObGenreContains(FIRST_GRADE.getGrade(), "10", obGenre);
    }

    public List<OldBook> genreForAnotherGrade(String obGenre) {
        return oldBookRepository.findAllByObStateInAndObGenreContains(List.of(SECOND_GRADE.getGrade(), THIRD_GRADE.getGrade()), obGenre);
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

    public OldBook view(String obNo) {
        return oldBookRepository.findById(Long.valueOf(obNo)).orElseThrow(() -> new NoSuchElementException("해당하는 중고책 없음"));
    }

    public void updateOldBook(int obNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBook -> oldBook.setObDonor("10"));
    }

    public Map<String, Object> selectGiveList(String obUserid) {
        return oldBookRepository.selectGiveList(obUserid);
    }

    public void updateRentOldBook(String rentNo) {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(Long.valueOf(rentNo));
        optionalOldBook.ifPresent(oldBook -> oldBook.setObDonor("1"));
    }

    public List<OldBook> selectOldBookTop3(String userId) {
        return oldBookRepository.findTop3ByObDonorOrderByObNameDesc(userId);
    }

    public List<Object> rent3List(String rentId) {
        return oldBookRepository.rent3list(rentId);
    }

    public List<Object> rentListAll(String rentId) {
        return oldBookRepository.rentListAll(rentId);
    }

    public List<RentInfoDto> getRentInfo(String rentId) {
        return oldBookRepository.getRentInfo(rentId);
    }

}
