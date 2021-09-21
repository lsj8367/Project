package pack.repository;

import java.util.List;
import pack.domain.entity.OldBook;

public interface OldBookRepositorySupport {
    OldBook getMostRentBook();
    List<OldBook> genreForFirstGrade(String obGenre);
    List<OldBook> genreForAnotherGrade(String obGenre);
}
