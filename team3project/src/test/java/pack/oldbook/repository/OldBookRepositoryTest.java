package pack.oldbook.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import pack.common.enums.Grade;
import pack.config.QuerydslConfig;
import pack.oldbook.domain.OldBook;
import pack.user.domain.User;
import pack.user.repository.UserRepository;

@DataJpaTest
@Import(QuerydslConfig.class)
@Sql(scripts = "classpath:data.sql")
class OldBookRepositoryTest {

    @Autowired
    OldBookRepository oldBookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("1등급 책 조회")
    void selectSellObAll() {
        List<OldBook> oldBookList = oldBookRepository.findByObState("1");
        assertThat(oldBookList.get(0).getObDonor()).isEqualTo("강호동");
    }

    @Test
    @DisplayName("준비중인 책 조회")
    void selectStandByAll() {
        List<OldBook> oldBookList = oldBookRepository.findByObState("0");
        assertThat(oldBookList.get(0).getObDonor()).isEqualTo("test6");
    }

    @Test
    @DisplayName("2,3등급 조회")
    void selectRentBookAll() {
        List<OldBook> oldBookList = oldBookRepository.findByObStateOrObState("2", "3");
        assertThat(oldBookList.get(0).getObDonor()).isEqualTo("태은희");
    }

    @Test
    @DisplayName("4,5등급 조회")
    void selectReuseAll() {
        List<OldBook> oldBookList = oldBookRepository.findByObStateOrObState("4", "5");
        assertThat(oldBookList.get(0).getObDonor()).isEqualTo("유재석");
    }

    @Test
    void getMostRentBook() {
        List<OldBook> oldBook = oldBookRepository.getMostRentBook();
        assertThat(oldBook.get(0).getObDonor()).isEqualTo("test6");
    }

    @Test
    void upObState() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObState("2");
            oldBookRepository.saveAndFlush(oldBook);
        });

        Optional<OldBook> result = oldBookRepository.findById(1L);
        result.ifPresent(oldBook -> {
            assertThat(oldBook.getObState()).isEqualTo("2");
        });
    }

    @Test
    void rmOldBook() {
        final Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);

        assertTrue(optionalOldBook.isPresent());

        optionalOldBook.ifPresent(oldBook -> {
            oldBookRepository.delete(oldBook);
            testEntityManager.flush();
        });

        final Optional<OldBook> result = oldBookRepository.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void oldBookGenreForFirstGrade() {
        List<OldBook> resultList = oldBookRepository.findAllByObStateAndObDonorNotAndObGenreContains(Grade.FIRST_GRADE.getGrade(), "10", "자격증");
        assertThat(resultList.get(0).getObDonor()).isEqualTo("강호동");
    }

    @Test
    void oldBookGenreForAnotherGrade() {
        List<OldBook> resultList = oldBookRepository.findAllByObStateInAndObGenreContains(List.of(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade()), "대학교재");
        assertThat(resultList.get(0).getObName()).isEqualTo("거시경제학");
    }

    @Test
    void oldRandom() {
        List<OldBook> resultList = oldBookRepository.oldRandom();
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    void selectBestRentBook() {
        OldBook oldBookList = oldBookRepository.selectBestRentBook();
        assertThat(oldBookList.getObNo()).isEqualTo(6);
    }

    @Test
    void oldHigh() {
        List<OldBook> resultList = oldBookRepository.findAllByObStateAndObDonorNotOrderByObNoDesc(Grade.FIRST_GRADE.getGrade(), "10");
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    void oldLowLimit2() {
        List<OldBook> resultList = oldBookRepository.oldLowLimit2();
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    void oldLow() {
        List<OldBook> resultList = oldBookRepository.findAllByObStateOrObStateOrderByObNoDesc(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade());
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    void insertOldBook() {
        OldBook oldBook = oldBookRepository.saveAndFlush(OldBook.builder()
            .obName("책제목")
            .obAuthor("작가")
            .obInter("역자")
            .obGenre("장르")
            .obComp("출판사")
            .obBdate(LocalDateTime.of(2021, 9, 23, 0, 0, 0))
            .obPrice(50000)
            .obDonor("test")
            .obUserid("1")
            .build());

        assertThat(oldBook.getObNo()).isEqualTo(9L);
    }

    @Test
    void getDataAllExist() {
        List<OldBook> resultList = oldBookRepository.findAllByObNameContainsOrderByObNoDesc("기본");
        assertThat(resultList.size()).isEqualTo(3);
    }

    @Test
    void oldBookInfo() {
        OldBook result = oldBookRepository.findByObStateAndObNo("1", 1L);
        assertThat(result.getObState()).isEqualTo("1");
        assertThat(result.getObDonor()).isEqualTo("강호동");
    }

    @Test
    void oldBookInfo2() {
        OldBook result = oldBookRepository.findByObStateInAndObNo(List.of(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade()), 3L);
        assertThat(result.getObState()).isEqualTo("2");
        assertThat(result.getObDonor()).isEqualTo("태은희");
    }

    @Test
    void upObProcess() {
        Long rentNo = 1L;
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(rentNo);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObDonor("0");
            oldBookRepository.saveAndFlush(oldBook);
            assertThat(oldBook.getObDonor()).isEqualTo("0");
        });
    }

    @Test
    void getObp() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);
        optionalOldBook.ifPresent(oldBook -> {
            assertThat(oldBook.getObPrice()).isEqualTo(30000);
        });
    }

    @Test
    void readCntUpdate() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObReadcnt(oldBook.getObReadcnt() + 1);
            oldBookRepository.saveAndFlush(oldBook);
            assertThat(oldBook.getObReadcnt()).isEqualTo(1);
        });
    }

    @Test
    @DisplayName("donor3List, donorListAll 같음")
    void donor3List() {
        List<OldBook> oldBookList = oldBookRepository.findTop3ByObDonorOrderByObNameDesc("유재석");
        System.out.println(oldBookList);
    }

    @Test
    void viewOldBook() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);
        optionalOldBook.ifPresent(oldBook -> {
            assertThat(oldBook.getObReadcnt()).isEqualTo(0);
        });
    }

    @Test
    void updateOldBook() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObDonor("10");
            oldBookRepository.saveAndFlush(oldBook);
            assertThat(oldBook.getObDonor()).isEqualTo("10");
        });
    }

    @Test
    void selectGiveList() {
        userRepository.save(User.builder()
                .userId("1")
            .build());

        oldBookRepository.selectGiveList("1");
    }

    @Test
    void updateRentOldBook() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(1L);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObDonor("1");
            oldBookRepository.saveAndFlush(oldBook);
            assertThat(oldBook.getObDonor()).isEqualTo("1");
        });
    }
}