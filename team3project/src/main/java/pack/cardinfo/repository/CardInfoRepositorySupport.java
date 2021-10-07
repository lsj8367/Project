package pack.cardinfo.repository;

import java.util.List;
import pack.cardinfo.domain.CardInfo;

public interface CardInfoRepositorySupport {
    List<CardInfo> cardList(String cardOwnerId);
}
