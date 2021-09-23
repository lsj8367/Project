package pack.repository;

import static pack.domain.entity.QOldBook.oldBook;
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
            .where(oldBook.obState.in("2", "3")
                .and(oldBook.obGenre.contains(obGenre)))
            .fetch();
    }

}
