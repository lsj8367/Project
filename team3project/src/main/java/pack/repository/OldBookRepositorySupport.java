package pack.repository;

import java.util.List;
import pack.domain.entity.OldBook;

public interface OldBookRepositorySupport {
    OldBook getMostRentBook();
    List<OldBook> genreForFirstGrade(String obGenre);
    List<OldBook> genreForAnotherGrade(String obGenre);
    List<OldBook> oldRandom();
    List<OldBook> oldLowLimit2();
    OldBook selectBestRentBook();
    List<OldBook> oldHigh();
    List<OldBook> oldLow();
    List<OldBook> getDataAllExist(String obName);
    OldBook oldBookInfoRentalState(Long obNo);
}
