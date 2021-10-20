package pack.user.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.cardinfo.domain.CardInfo;
import pack.cardinfo.repository.CardInfoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CardInfoService {

    private final CardInfoRepository cardInfoRepository;

    public ModelAndView getCardInfo(String id, ModelAndView modelAndView) {
        Optional<CardInfo> optionalCardInfo = cardInfoRepository.findById(id);
        optionalCardInfo.ifPresent(cardInfo -> {
            modelAndView.addObject("cardDto", cardInfo);
        });

        return modelAndView;
    }

    public List<CardInfo> getCardList(String cardOwnerId) {
        return cardInfoRepository.cardList(cardOwnerId);
    }

    public void insertCard(CardInfo cardInfo) {
        if (Objects.isNull(cardInfo)) {
            throw new RuntimeException("카드 정보가 없습니다.");
        }
        cardInfoRepository.save(cardInfo);
    }

}
