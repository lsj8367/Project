package pack.repository;

import static pack.domain.entity.QOldBook.oldBook;
import static pack.domain.entity.QUser.user;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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
    public List<OldBook> genreForFirstGrade(String obGenre) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(Grade.FIRST_GRADE.getGrade())
                .and(oldBook.obDonor.ne("10"))
                .and(oldBook.obGenre.contains(obGenre)))
            .fetch();
    }

    @Override
    public List<OldBook> genreForAnotherGrade(String obGenre) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.in(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade())
                .and(oldBook.obGenre.contains(obGenre)))
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
    public List<OldBook> oldHigh() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(Grade.FIRST_GRADE.getGrade())
                .and(oldBook.obDonor.ne("10")))
            .orderBy(oldBook.obNo.desc())
            .fetch();
    }

    @Override
    public List<OldBook> oldLow() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(Grade.SECOND_GRADE.getGrade())
                .or(oldBook.obState.eq(Grade.THIRD_GRADE.getGrade())))
            .orderBy(oldBook.obNo.desc())
            .fetch();
    }

    @Override
    public List<OldBook> getDataAllExist(String obName) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obName.contains(obName))
            .orderBy(oldBook.obNo.desc())
            .fetch();
    }

    @Override
    public OldBook oldBookInfoRentalState(Long obNo) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.in("2", "3")
                .and(oldBook.obNo.eq(obNo)))
            .fetchOne();
    }

    @Override
    public List<OldBook> donorList(String userId) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obDonor.eq(userId))
            .orderBy(oldBook.obName.desc())
            .limit(3)
            .fetch();
    }

    @Override
    public Tuple selectGiveList(String obUserId) {
        return jpaQueryFactory.select(user.userName, oldBook).from(oldBook)
            .innerJoin(user).on(oldBook.obUserid.eq(user.userId))
            .where(oldBook.obUserid.eq(obUserId))
            .fetchOne();
    }
}
