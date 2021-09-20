package pack.repository;

import java.util.List;
import pack.domain.entity.NewBook;

public interface NewBookRepositorySupport {
    NewBook selectBestSeller();

    void rollbackStock(final int nbStock, final Long id);

    List<NewBook> selectBest30();

    List<NewBook> selectGenre(final String nbGenre);

    List<NewBook> selectAuthorList(String nbAuthor);

    List<NewBook> getDataNewAllExist(String nbName);

    List<NewBook> selectReadTop3();

    List<NewBook> selectRandom(int limit);

    List<NewBook> selectNew();

    NewBook recommandNewBook();

    NewBook selectBest();
}
