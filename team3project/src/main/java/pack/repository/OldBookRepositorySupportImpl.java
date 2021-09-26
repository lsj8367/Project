package pack.repository;

import static pack.domain.entity.QOldBook.oldBook;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pack.domain.entity.OldBook;

@RequiredArgsConstructor
public class OldBookRepositorySupportImpl implements OldBookRepositorySupport {

    private static final String FIRST_GRADE = "1";
    private static final String SECOND_GRADE = "2";
    private static final String THIRD_GRADE = "3";


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public OldBook getMostRentBook() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obScount.eq(JPAExpressions.select(oldBook.obScount.max())
                .from(oldBook)))
            .fetchOne();
    }

    @Override
    public List<OldBook> genreForFirstGrade(String obGenre) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(FIRST_GRADE).and(oldBook.obDonor.ne("10"))
                .and(oldBook.obGenre.contains(obGenre)))
            .fetch();
    }

    @Override
    public List<OldBook> genreForAnotherGrade(String obGenre) {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.in(SECOND_GRADE, THIRD_GRADE)
                .and(oldBook.obGenre.contains(obGenre)))
            .fetch();
    }

    @Override
    public List<OldBook> oldRandom() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(FIRST_GRADE))
            .orderBy(NumberExpression.random().desc())
            .limit(2)
            .fetch();
    }

    @Override
    public List<OldBook> oldLowLimit2() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(SECOND_GRADE)
                .or(oldBook.obState.eq(THIRD_GRADE)))
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
            .where(oldBook.obState.eq(FIRST_GRADE))
            .orderBy(oldBook.obNo.desc())
            .fetch();
    }

    @Override
    public List<OldBook> oldLow() {
        return jpaQueryFactory.selectFrom(oldBook)
            .where(oldBook.obState.eq(SECOND_GRADE)
                .or(oldBook.obState.eq(THIRD_GRADE)))
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

}
