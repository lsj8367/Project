package pack.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.model.CardInfoDto;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.BuyDao;
import pack.user.model.CardInfoDao;
import pack.user.model.OldBookDao;

@Service
@RequiredArgsConstructor
public class BuyService {
    private final OldBookDao oldBookDao;
    private final BuyDao buyDao;
    private final CardInfoDao cardInfoDao;

    public ModelAndView buy(String ob_no, String user_id) {
        ModelAndView modelAndView = new ModelAndView();
        OldBookDto dto = oldBookDao.bookInfo(ob_no);
        UserDto user = buyDao.point(user_id);
        CardInfoDto card = cardInfoDao.selectCard(user_id);

        modelAndView.addObject("buyinfo", dto);
        modelAndView.addObject("point", user);
        modelAndView.addObject("card", card);

        return modelAndView;
    }
}
