package pack.oldbook.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.oldbook.domain.OldBook;

public interface OldBookRepository extends JpaRepository<OldBook, Long>, OldBookRepositorySupport {
    List<OldBook> findByObState(String obState);
    List<OldBook> findByObStateOrObState(String obState1, String obState2);
    OldBook findByObStateAndObNo(String obState, Long obNo);
    List<OldBook> findAllByObStateAndObDonorNotAndObGenreContains(String obState, String obDonor, String obGenre);
    List<OldBook> findAllByObStateAndObDonorNotOrderByObNoDesc(String obState, String obDonor);
    List<OldBook> findAllByObStateOrObStateOrderByObNoDesc(String obState, String obState2);
    List<OldBook> findAllByObNameContainsOrderByObNoDesc(String obName);
    List<OldBook> findTop3ByObDonorOrderByObNameDesc(String obDonor);
    List<OldBook> findAllByObStateInAndObGenreContains(List<String> obState, String obGenre);
    OldBook findByObStateInAndObNo(List<String> obState, Long obNo);

}