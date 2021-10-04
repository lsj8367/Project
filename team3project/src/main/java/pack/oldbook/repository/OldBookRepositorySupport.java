package pack.oldbook.repository;

import java.util.List;
import java.util.Map;
import pack.oldbook.domain.OldBook;

public interface OldBookRepositorySupport {
    List<OldBook> getMostRentBook();
    List<OldBook> oldRandom();
    List<OldBook> oldLowLimit2();
    OldBook selectBestRentBook();
    Map<String, Object> selectGiveList(String obUserId);
}
