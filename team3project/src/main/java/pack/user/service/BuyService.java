package pack.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.CardInfo;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.repository.CardInfoRepository;
import pack.user.model.BuyDao;
import pack.user.model.OldBookDao;

@Service
@RequiredArgsConstructor
public class BuyService {
    private final OldBookDao oldBookDao;
    private final BuyDao buyDao;
    private final CardInfoRepository cardInfoRepository;

    public ModelAndView buy(String ob_no, String user_id) {
        ModelAndView modelAndView = new ModelAndView();
        OldBookDto dto = oldBookDao.bookInfo(ob_no);
        UserDto user = buyDao.point(user_id);

        Optional<CardInfo> optionalCardInfo = cardInfoRepository.findById(user_id);
        optionalCardInfo.ifPresent(cardInfo -> {
            modelAndView.addObject("card", cardInfo);
        });

        modelAndView.addObject("buyinfo", dto);
        modelAndView.addObject("point", user);

        modelAndView.setViewName("buy");
        return modelAndView;
    }
}
