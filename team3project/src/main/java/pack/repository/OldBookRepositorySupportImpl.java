package pack.repository;

import static pack.domain.entity.QOldBook.oldBook;
import static pack.domain.entity.QUser.user;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import pack.domain.entity.OldBook;
import pack.model.Grade;
import pack.repository.template.MariaDBTemplates;

@RequiredArgsConstructor
public class OldBookRepositorySupportImpl implements OldBookRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<OldBook> getMostRentBook() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obScount.eq(JPAExpressions.select(oldBook.obScount.max())
                .from(oldBook)))
            .fetch();
    }

    @Override
    public List<OldBook> oldRandom() {
        JPAQuery<OldBook> jpaQuery = new JPAQuery<>(entityManager, MariaDBTemplates.DEFAULT);
        return jpaQuery.select(oldBook).from(oldBook)
            .where(oldBook.obState.eq(Grade.FIRST_GRADE.getGrade()))
            .orderBy(NumberExpression.random().desc())
            .limit(2)
            .fetch();
    }

    @Override
    public List<OldBook> oldLowLimit2() {
        JPAQuery<OldBook> jpaQuery = new JPAQuery<>(entityManager, MariaDBTemplates.DEFAULT);
        return jpaQuery.select(oldBook).from(oldBook)
            .where(oldBook.obState.eq(Grade.SECOND_GRADE.getGrade())
                .or(oldBook.obState.eq(Grade.THIRD_GRADE.getGrade())))
            .orderBy(NumberExpression.random().desc())
            .limit(2)
            .fetch();
    }

    @Override
    public OldBook selectBestRentBook() {
        return jpaQueryFactory.selectFrom(oldBook)
            .orderBy(oldBook.obScount.desc())
            .limit(1)
            .fetchOne();
    }

    @Override
    public Map<String, Object> selectGiveList(String obUserId) {
        Tuple tuple = jpaQueryFactory.select(user, oldBook).from(oldBook)
            .innerJoin(user).on(oldBook.obUserid.eq(user.userId))
            .where(oldBook.obUserid.eq(obUserId))
            .fetchOne();

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", tuple.get(user.userId));
        map.put("user_name", tuple.get(user.userName));
        map.put("ob_no", tuple.get(oldBook.obNo));
        map.put("ob_name", tuple.get(oldBook.obName));
        map.put("ob_author", tuple.get(oldBook.obAuthor));
        map.put("ob_ddate", tuple.get(oldBook.obDdate));
        map.put("ob_comp", tuple.get(oldBook.obComp));
        map.put("ob_comment", tuple.get(oldBook.obComment));
        map.put("ob_genre", tuple.get(oldBook.obGenre));
        map.put("ob_inter", tuple.get(oldBook.obInter));
        map.put("ob_donor", tuple.get(oldBook.obDonor));

        return map;
    }
}
