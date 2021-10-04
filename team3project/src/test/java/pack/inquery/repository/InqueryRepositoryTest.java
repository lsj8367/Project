package pack.inquery.repository;

import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.inquery.domain.Inquery;

@DataJpaTest
@Import(QuerydslConfig.class)
public class InqueryRepositoryTest {
    @Autowired
    InqueryRepository inqueryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        //insertInquery
        Inquery inquery = Inquery
                .builder()
                .inqTitle("제목")
                .inqContext("답변")
                .inqId("test")
                .inqGnum(0)
                .inqOnum(0)
                .inqNested(0)
                .build();
        inqueryRepository.save(inquery);

        testEntityManager.clear();
    }

    @Test
    void selectinqAll() {
        inqueryRepository.findAllOrderByInqOnumASC();
    }

    @Test
    @Transactional
    void insertinqReply() {
        Inquery inquery = Inquery
                .builder()
                .inqTitle("제목")
                .inqContext("답변")
                .inqId("test1234")
                .inqGnum(0)
                .inqOnum(0)
                .inqNested(0)
                .build();
        inqueryRepository.save(inquery);
        testEntityManager.clear();
    }

    @Test
    @DisplayName("selectInqList, inq3list")
    void selectInqList() {
        inqueryRepository.findByInqIdOrderByInqNoAsc("test");
    }

    @Test
    void getMaxNum() {
        inqueryRepository.getMaxNum();
    }

    @Test
    void selectInqPart() {
        inqueryRepository.findById(1L);
    }

    @Test
    void updateOnum() {
        inqueryRepository.updateOnum(2, 1);
    }


}
