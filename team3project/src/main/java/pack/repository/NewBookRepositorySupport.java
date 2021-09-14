package pack.repository;

import com.querydsl.core.Tuple;
import pack.domain.entity.NewBook;

public interface NewBookRepositorySupport {
    NewBook selectBestSeller();
    Tuple getMostSellBook();
    void upNbstock(int plusStock, Long id);
}
