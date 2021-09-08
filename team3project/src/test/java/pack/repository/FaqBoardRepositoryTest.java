package pack.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.domain.entity.FaqBoard;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
class FaqBoardRepositoryTest {
    @Autowired
    FaqBoardRepository faqBoardRepository;

    @Test
    @DisplayName("selectFaqAll, selectQnaAll")
    void selectFaqAll() {
        faqBoardRepository.findAll();
    }

    @Test
    @DisplayName("selectfaqType, selectQnaClass")
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
}