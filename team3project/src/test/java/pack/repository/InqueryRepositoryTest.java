package pack.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.domain.entity.Inquery;

import javax.transaction.Transactional;

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
    void selectingAll() {
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
                .inqOnum(1)
                .inqNested(1)
                .build();
        inqueryRepository.save(inquery);
        testEntityManager.clear();
    }

    @Test
    void selectInqList() {

    }
}
