package pack.repository;

import java.util.List;
import java.util.Map;
import pack.domain.entity.OldBook;

public interface OldBookRepositorySupport {
    List<OldBook> getMostRentBook();
    List<OldBook> oldRandom();
    List<OldBook> oldLowLimit2();
    OldBook selectBestRentBook();
    Map<String, Object> selectGiveList(String obUserId);
}
