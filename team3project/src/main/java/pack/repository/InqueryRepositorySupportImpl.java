package pack.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pack.domain.entity.Inquery;

import java.util.List;

import static pack.domain.entity.QInquery.inquery;

@RequiredArgsConstructor
public class InqueryRepositorySupportImpl implements InqueryRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Inquery> findAllOrderByInqOnumASC() {
        return jpaQueryFactory.selectFrom(inquery)
                .orderBy(inquery.inqOnum.asc()).fetch();
    }

    @Override
    public List<Inquery> selectInqList(final String inqId) {
//        List<Tuple> tupleList = jpaQueryFactory.select(inquery.inqTitle, inquery.inqContext, inquery.inqDdate).from(inquery)
//                .where(inquery.inqId.eq(inqId)).fetch();
//
//        List<Inquery> resultList = new ArrayList<>();
//        for (Tuple tuple : tupleList) {
//            resultList.add(tuple.get(inquery.));
//        }
        return null;
    }
}