package pack.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import pack.config.QuerydslConfig;
import pack.domain.entity.OldBook;

@DataJpaTest
@Import(QuerydslConfig.class)
@Sql(scripts = "classpath:data.sql")
class OldBookRepositoryTest {
    @Autowired
    OldBookRepository oldBookRepository;

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
    void obthrow() {
        Optional<OldBook> optionalOldBook = oldBookRepository.findById(7L);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObState("4");
        });
        testEntityManager.flush();
        testEntityManager.clear();
        Optional<OldBook> optionalOldBook2 = oldBookRepository.findById(7L);
        optionalOldBook2.ifPresent(oldBook -> {
            assertThat(oldBook.getObState()).isEqualTo("4");
        });
    }

    @Test
    void getMostRentBook() {
        OldBook oldBook = oldBookRepository.getMostRentBook();
        assertThat(oldBook.getObDonor()).isEqualTo("test6");
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
    void oldGenre() {
        List<OldBook> resultList = oldBookRepository.genreForFirstGrade("자격증");
        assertThat(resultList.get(0).getObDonor()).isEqualTo("강호동");
    }

    @Test
    void oldGenre2() {
        List<OldBook> resultList = oldBookRepository.genreForAnotherGrade("대학교재");
        assertThat(resultList.get(0).getObName()).isEqualTo("거시경제학");
    }
}