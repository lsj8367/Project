package pack.repository;

import com.querydsl.core.Tuple;
import java.util.List;

public interface CardInfoRepositorySupport {
    List<Tuple> cardList(String cardOwnerId);
}
