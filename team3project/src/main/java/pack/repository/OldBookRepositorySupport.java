package pack.repository;

import com.querydsl.core.Tuple;
import java.util.List;
import pack.domain.entity.OldBook;

public interface OldBookRepositorySupport {
    List<OldBook> getMostRentBook();
    List<OldBook> oldRandom();
    List<OldBook> oldLowLimit2();
    OldBook selectBestRentBook();
    Tuple selectGiveList(String obUserId);
}
