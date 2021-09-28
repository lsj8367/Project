package pack.repository;

import static pack.domain.entity.QNewBook.newBook;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import pack.domain.entity.NewBook;
import pack.repository.template.MariaDBTemplates;

@RequiredArgsConstructor
public class NewBookRepositorySupportImpl implements NewBookRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public NewBook selectBestSeller() {
        return jpaQueryFactory.selectFrom(newBook)
            .where(newBook.nbScount
                .eq(JPAExpressions.select(newBook.nbScount.max())
                    .from(newBook)))
            .fetchOne();
    }

    @Override
    public void rollbackStock(int nbStock, Long id) {
        jpaQueryFactory.update(newBook)
            .set(newBook.nbStock, newBook.nbStock.add(nbStock))
            .set(newBook.nbScount, newBook.nbScount.subtract(nbStock))
            .where(newBook.id.eq(id))
            .execute();
    }

    @Override
    public List<NewBook> selectBest30() {
        return jpaQueryFactory.selectFrom(newBook).orderBy(newBook.nbScount.desc())
            .limit(30).fetch();
    }

    @Override
    public List<NewBook> selectGenre(String nbGenre) {
        return jpaQueryFactory.selectFrom(newBook)
            .where(newBook.nbGenre.contains(nbGenre))
            .fetch();
    }

    @Override
    public List<NewBook> selectAuthorList(String nbAuthor) {
        return jpaQueryFactory.selectFrom(newBook)
            .where(newBook.nbAuthor.eq(nbAuthor))
            .limit(3)
            .fetch();
    }

    @Override
    public List<NewBook> getDataNewAllExist(String nbName) {
        return jpaQueryFactory.selectFrom(newBook)
            .where(newBook.nbName.contains(nbName))
            .orderBy(newBook.id.desc())
            .fetch();
    }

    @Override
    public List<NewBook> selectReadTop3() {
        return jpaQueryFactory.selectFrom(newBook)
            .orderBy(newBook.nbReadcnt.desc())
            .limit(3)
            .fetch();
    }

    @Override
    public List<NewBook> selectRandom(int limit) {
        JPAQuery<NewBook> jpaQuery = new JPAQuery<>(entityManager, MariaDBTemplates.DEFAULT);
        return jpaQuery.from(newBook)
                .orderBy(NumberExpression.random().asc())
                    .limit(limit)
                .fetch();
    }

    @Override
    public List<NewBook> selectNew() {
        return jpaQueryFactory.selectFrom(newBook)
            .orderBy(newBook.nbBdate.desc())
            .fetch();
    }

    @Override
    public NewBook recommandNewBook() {
        JPAQuery<NewBook> jpaQuery = new JPAQuery<>(entityManager, MariaDBTemplates.DEFAULT);
        return jpaQuery.from(newBook)
            .orderBy(NumberExpression.random().desc())
            .limit(1)
            .fetchOne();
    }

    @Override
    public NewBook selectBest() {
        return jpaQueryFactory.selectFrom(newBook)
            .orderBy(newBook.nbScount.desc()).limit(1)
            .fetchOne();
    }

}
