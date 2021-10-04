package pack.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import pack.config.QuerydslConfig;
import pack.newbook.domain.NewBook;
import pack.newbook.repository.NewBookRepository;

@DataJpaTest
@Import(QuerydslConfig.class)
class NewBookRepositoryTest {
    @Autowired
    NewBookRepository newBookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void selectBestSeller() {
        newBookRepository.selectBestSeller();
    }

    @Test
    void insertBookData() {
        newBookRepository.saveAndFlush(NewBook.builder()
                .nbName("홍길동")
                .nbAuthor("지은이")
                .nbInter("")
                .nbGenre("시")
                .nbComp("출판사")
                .nbBdate(LocalDateTime.now())
                .nbStock(123)
                .nbPrice(13000)
                .nbPlot("소개글 한마디")
                .nbImage("aladin.image.asdasd")
                .build());
    }

    @Test
    void upNbstock() {
        newBookRepository.saveAndFlush(NewBook.builder()
                .nbName("홍길동")
                .nbAuthor("지은이")
                .nbInter("")
                .nbGenre("시")
                .nbComp("출판사")
                .nbBdate(LocalDateTime.now())
                .nbStock(123)
                .nbPrice(13000)
                .nbPlot("소개글 한마디")
                .nbImage("aladin.image.asdasd")
                .build());
        testEntityManager.clear();

        Optional<NewBook> optionalNewBook = newBookRepository.findById(1L);
        optionalNewBook.ifPresent(newBook -> {
            newBook.setNbStock(newBook.getNbStock() + 100);
        });

        Optional<NewBook> optionalNewBook2 = newBookRepository.findById(1L);
        optionalNewBook2.ifPresent(newBook -> {
            assertThat(newBook.getNbStock()).isEqualTo(223);
        });
    }

    @Test
    void rollbackStock() {
        newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("홍길동")
            .nbAuthor("지은이")
            .nbInter("")
            .nbGenre("시")
            .nbComp("출판사")
            .nbBdate(LocalDateTime.now())
            .nbStock(123)
            .nbPrice(13000)
            .nbPlot("소개글 한마디")
            .nbImage("aladin.image.asdasd")
            .build());
        testEntityManager.clear();

        newBookRepository.rollbackStock(100, 1L);
    }

    @Test
    void selectGenre() {
        newBookRepository.selectGenre("시");
    }

    @Test
    void selectNewBook() {
        newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("홍길동")
            .nbAuthor("지은이")
            .nbInter("")
            .nbGenre("시")
            .nbComp("출판사")
            .nbBdate(LocalDateTime.of(2021, 9, 15, 0, 0, 0))
            .nbStock(123)
            .nbPrice(13000)
            .nbPlot("소개글 한마디")
            .nbImage("aladin.image.asdasd")
            .build());
        testEntityManager.clear();

        Optional<NewBook> optionalNewBook = newBookRepository.findById(1L);

        optionalNewBook.ifPresent(newBook -> {
            assertThat(newBook.getNbBdate()).isEqualTo("2021-09-15");
        });
    }

    @Test
    void plusReadCnt() {
        NewBook newBook = newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("홍길동")
            .nbAuthor("지은이")
            .nbInter("")
            .nbGenre("시")
            .nbComp("출판사")
            .nbBdate(LocalDateTime.now())
            .nbStock(123)
            .nbPrice(13000)
            .nbPlot("소개글 한마디")
            .nbImage("aladin.image.asdasd")
            .build());

        newBook.setNbReadcnt(newBook.getNbReadcnt() + 1);

        assertThat(newBook.getNbReadcnt()).isEqualTo(1);
    }

    @Test
    void selectAuthorList() {
        newBookRepository.selectAuthorList("작가");
    }

    @Test
    void minusNewBookStock() {
        NewBook newBook = newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("홍길동")
            .nbAuthor("지은이")
            .nbInter("")
            .nbGenre("시")
            .nbComp("출판사")
            .nbBdate(LocalDateTime.now())
            .nbStock(100)
            .nbPrice(13000)
            .nbPlot("소개글 한마디")
            .nbImage("aladin.image.asdasd")
            .build());

        newBook.setNbStock(newBook.getNbStock() - 1);

        assertThat(newBook.getNbStock()).isEqualTo(99);
    }

    @Test
    void plusNewBookScount() {
        NewBook newBook = newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("홍길동")
            .nbAuthor("지은이")
            .nbInter("")
            .nbGenre("시")
            .nbComp("출판사")
            .nbBdate(LocalDateTime.now())
            .nbStock(100)
            .nbPrice(13000)
            .nbPlot("소개글 한마디")
            .nbImage("aladin.image.asdasd")
            .build());

        newBook.setNbScount(newBook.getNbScount() + 1);

        assertThat(newBook.getNbScount()).isEqualTo(1);
    }

    @Test
    void getDataNewAllExist() {
        newBookRepository.getDataNewAllExist("테스트");
    }

    @Test
    void selectReadTop3() {
        newBookRepository.selectReadTop3();
    }

    @Test
    void selectRandom10() {
        newBookRepository.selectRandom(10);
    }

    @Test
    void selectNew() {
        newBookRepository.selectNew();
    }

    @Test
    void recommendNewBook() {
        newBookRepository.selectRandom(1);
    }

    @Test
    void selectBookDataAll() {
        newBookRepository.findAll();
    }

    @Test
    void upnbc() {
        NewBook newBook = newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("홍길동")
            .nbAuthor("지은이")
            .nbInter("")
            .nbGenre("시")
            .nbComp("출판사")
            .nbBdate(LocalDateTime.now())
            .nbStock(100)
            .nbPrice(13000)
            .nbPlot("소개글 한마디")
            .nbImage("aladin.image.asdasd")
            .build());

        newBook.setNbScount(newBook.getNbScount() - 1);
        newBook.setNbStock(newBook.getNbStock() + 1);
    }

    @Test
    void recommandNewBook() {
        newBookRepository.recommandNewBook();
    }

    @Test
    void selectBookAll() {
        newBookRepository.findAll(Sort.by(Direction.DESC, "id"));
    }

}