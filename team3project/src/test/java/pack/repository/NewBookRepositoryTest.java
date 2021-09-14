package pack.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.domain.entity.NewBook;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    void getMostSellBook() {
        newBookRepository.getMostSellBook();
    }

    @Test
    void insertBookData() {
//        insert into newbook values(default,#{nb_name},#{nb_author},#{nb_inter},
        //        #{nb_genre},#{nb_comp},date(#{nb_bdate}),#{nb_stock},#{nb_price}
        //        ,0,0,#{nb_plot},#{nb_image})

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
}