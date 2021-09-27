package pack.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.CardInfo;
import pack.model.UserDto;
import pack.repository.CardInfoRepository;
import pack.repository.OldBookRepository;
import pack.user.model.BuyDao;

@Service
@RequiredArgsConstructor
public class BuyService {
    private final BuyDao buyDao;
    private final CardInfoRepository cardInfoRepository;
    private final OldBookRepository oldBookRepository;

    public ModelAndView buy(String ob_no, String user_id) {
        ModelAndView modelAndView = new ModelAndView();

        UserDto user = buyDao.point(user_id);

        Optional<CardInfo> optionalCardInfo = cardInfoRepository.findById(user_id);
        optionalCardInfo.ifPresent(cardInfo -> {
            modelAndView.addObject("card", cardInfo);
        });

        modelAndView.addObject("buyinfo", oldBookRepository.findByObStateAndObNo("1", Long.valueOf(ob_no)));
        modelAndView.addObject("point", user);

        modelAndView.setViewName("buy");
        return modelAndView;
    }
}
