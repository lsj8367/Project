package pack.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import pack.domain.entity.NewBook;

import static pack.domain.entity.QNewBook.newBook;

@RequiredArgsConstructor
public class NewBookRepositorySupportImpl implements NewBookRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public NewBook selectBestSeller() {
        return jpaQueryFactory.selectFrom(newBook)
                .where(newBook.nbScount
                        .eq(JPAExpressions.select(newBook.nbScount.max())
                                .from(newBook)))
                .fetchOne();
    }

    @Override
    public Tuple getMostSellBook() {
        return jpaQueryFactory.select(newBook.id, newBook.nbName, newBook.nbAuthor,
                newBook.nbComp, newBook.nbScount)
                .from(newBook)
                .where(newBook.nbScount
                        .eq(JPAExpressions.select(newBook.nbScount.max())
                                .from(newBook)))
                .fetchOne();
    }

    @Override
    public void upNbstock(final int plusStock, final Long id) {
        jpaQueryFactory.update(newBook)
                .set(newBook.nbStock, newBook.nbStock.add(plusStock))
                .where(newBook.id.eq(id));
    }
}
