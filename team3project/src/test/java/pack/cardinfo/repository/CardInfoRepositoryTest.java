package pack.cardinfo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.cardinfo.domain.CardInfo;
import pack.config.QuerydslConfig;
import pack.user.domain.User;
import pack.user.repository.UserRepository;

@DataJpaTest
@Import(QuerydslConfig.class)
class CardInfoRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardInfoRepository cardInfoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void setUp() {
        CardInfo cardInfo = CardInfo.builder()
            .cardOwnerid("test")
            .cardOwner("테스트")
            .cardComp("국민")
            .cardNo("tetset")
            .cardPasswd("1234")
            .build();
        cardInfoRepository.saveAndFlush(cardInfo);

        User user = User.builder()
            .userId("test")
            .userName("테스트")
            .userPasswd("123")
            .userTel("010-2123-1231")
            .userAddr("경기도")
            .userZip("12345")
            .userMail("test@abc.com")
            .cardInfo(cardInfo)
            .build();
        userRepository.saveAndFlush(user);
        testEntityManager.clear();
    }

    @Test
    void selectCard() {
        Optional<CardInfo> optCardInfo = cardInfoRepository.findById("test");
        optCardInfo.ifPresent(cardInfo -> {
            assertThat(cardInfo.getCardOwner()).isEqualTo("테스트");
            assertThat(cardInfo.getCardComp()).isEqualTo("국민");
            assertThat(cardInfo.getCardNo()).isEqualTo("tetset");
            assertThat(cardInfo.getCardPasswd()).isEqualTo("1234");
        });
    }

    @Test
    @DisplayName("card3List, cardlistall 같은 문법 사용")
    void card3List() {
        List<Tuple> list = cardInfoRepository.cardList("테스트");
    }
}