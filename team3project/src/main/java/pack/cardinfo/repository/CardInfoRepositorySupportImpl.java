package pack.cardinfo.repository;

import static pack.cardinfo.domain.QCardInfo.cardInfo;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CardInfoRepositorySupportImpl implements CardInfoRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> cardList(String cardOwnerId) {
        return jpaQueryFactory.select(cardInfo.cardNo, cardInfo.cardComp).from(cardInfo)
            .where(cardInfo.cardOwnerid.eq(cardOwnerId))
            .orderBy(cardInfo.cardComp.desc())
            .fetch();
    }

}
