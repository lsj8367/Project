package pack.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.faqboard.domain.FaqBoard;
import pack.faqboard.repository.FaqBoardRepository;

@DataJpaTest
@Import(QuerydslConfig.class)
class FaqBoardRepositoryTest {
    @Autowired
    FaqBoardRepository faqBoardRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("selectFaqAll")
    void selectFaqAll() {
        faqBoardRepository.findAll();
    }

    @Test
    @DisplayName("selectQnaClass")
    void selectfaqType() {
        List<FaqBoard> faqBoardList = faqBoardRepository.findAllByFaqType("주문");
        assertThat(faqBoardList.size()).isEqualTo(0);
    }

    @Test
    @Transactional
    void insertFaqData() {
        FaqBoard faqBoard = FaqBoard.builder()
                .faqTitle("테스트")
                .faqContent("테스트")
                .faqType("주문")
                .build();

        faqBoardRepository.save(faqBoard);

        assertThat(faqBoard.getFaqNo()).isEqualTo(1L);
    }

    @Test
    void selectFaqDetail() {
//    select * from faqboard where faq_no =#{faq_no}
        FaqBoard faqBoard = FaqBoard.builder()
            .faqTitle("테스트")
            .faqContent("테스트")
            .faqType("주문")
            .build();

        faqBoardRepository.save(faqBoard);
        testEntityManager.clear();

        faqBoardRepository.findById(1L);
    }
}