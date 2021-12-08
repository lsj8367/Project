package pack.oldbook.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pack.oldbook.domain.OldBook;
import pack.rentinfo.model.RentInfoDto;

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

    //    최근 대여 내역 3 리스트
    @Query(value = "select ob_name, rent_no, date_format(rent_sdate, '%Y-%m-%d') as rent_sdate, date_format(rent_edate, '%Y-%m-%d') as rent_edate"
        + " from oldbook left outer join rentinfo on ob_no = rent_no"
        + " where rent_id = :rentId"
        + " order by rent_sdate desc limit 3", nativeQuery = true)
    List<Object> rent3list(@Param("rentId") String rentId);

    //    전체 대여 내역 리스트
    @Query(value = "select ob_name, rent_no, date_format(rent_sdate, '%Y-%m-%d') as rent_sdate, date_format(rent_edate, '%Y-%m-%d') as rent_edate"
        + " from oldbook"
        + " left outer join rentinfo on ob_no = rent_no"
        + " where rent_id = :rentId"
        + " order by rent_sdate desc", nativeQuery = true)
    List<Object> rentListAll(@Param("rentId") String rentId);

    @Query(value = "select rent_no, rent_id, date_format(rent_sdate, '%Y-%m-%d') as rent_sdate, date_format(rent_edate, '%Y-%m-%d') as rent_edate, rent_ecount"
        + " from rentinfo where rent_id = :rentId"
        + " order by rent_sdate desc limit 1", nativeQuery = true)
    List<RentInfoDto> getRentInfo(@Param("rentId") String rentId);

}