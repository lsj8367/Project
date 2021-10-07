package pack.cardinfo.repository;

import static pack.cardinfo.domain.QCardInfo.cardInfo;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pack.cardinfo.domain.CardInfo;

@RequiredArgsConstructor
public class CardInfoRepositorySupportImpl implements CardInfoRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CardInfo> cardList(String cardOwnerId) {
        final List<Tuple> tupleList = jpaQueryFactory.select(cardInfo.cardNo, cardInfo.cardComp).from(cardInfo)
            .where(cardInfo.cardOwnerid.eq(cardOwnerId))
            .orderBy(cardInfo.cardComp.desc())
            .fetch();

        List<CardInfo> cardInfoList = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            cardInfoList.add(CardInfo.builder()
                .cardNo(tuple.get(cardInfo.cardNo))
                .cardComp(tuple.get(cardInfo.cardComp))
                .build());
        }
        return cardInfoList;

    }

}
