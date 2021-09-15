package pack.repository;

import com.querydsl.core.Tuple;
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
                .orderBy(inquery.inqOnum.asc())
                .fetch();
    }

    @Override
    public List<Tuple> selectInqList(final String inqId) {
        return jpaQueryFactory
                .select(inquery.inqTitle, inquery.inqContext, inquery.inqDdate)
                .from(inquery)
                .where(inquery.inqId.eq(inqId))
                .orderBy(inquery.inqNo.asc())
                .fetch();
    }

    @Override
    public Long getMaxNum() {
        //coalesce 는 nvl의 역할 null일 경우에 해당 값으로 채움
        return jpaQueryFactory.select(inquery.inqNo.max().coalesce(0L))
                .from(inquery)
                .fetchOne();
    }

    @Override
    public Long updateOnum(int inqOnum, int inqGnum) {
        return jpaQueryFactory.update(inquery).set(inquery.inqOnum, inquery.inqOnum.add(1))
                .where(inquery.inqOnum.goe(inqOnum).and(inquery.inqGnum.eq(inqGnum))).execute();
    }

}