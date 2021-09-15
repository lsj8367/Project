package pack.user.service;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.CardInfo;
import pack.repository.CardInfoRepository;

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
}
