package pack.repository;

import com.querydsl.core.Tuple;
import java.util.List;
import pack.domain.entity.OldBook;

public interface OldBookRepositorySupport {
    List<OldBook> getMostRentBook();
    List<OldBook> genreForFirstGrade(String obGenre);
    List<OldBook> genreForAnotherGrade(String obGenre);
    List<OldBook> oldRandom();
    List<OldBook> oldLowLimit2();
    OldBook selectBestRentBook();
    List<OldBook> oldHigh();
    List<OldBook> oldLow();
    List<OldBook> getDataAllExist(String obName);
    OldBook oldBookInfoRentalState(Long obNo);
    List<OldBook> donorList(String userId);
    Tuple selectGiveList(String obUserId);
}
