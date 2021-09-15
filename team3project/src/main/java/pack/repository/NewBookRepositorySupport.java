package pack.repository;

import com.querydsl.core.Tuple;
import java.util.List;
import pack.domain.entity.NewBook;

public interface NewBookRepositorySupport {
    NewBook selectBestSeller();

    Tuple getMostSellBook();

    void rollbackStock(final int nbStock, final Long id);

    List<NewBook> selectGenre(final String nbGenre);

    List<NewBook> selectAuthorList(String nbAuthor);

    List<NewBook> getDataNewAllExist(String nbName);

    List<NewBook> selectReadTop3();

    List<NewBook> selectRandom(int limit);

    List<NewBook> selectNew();
}
