package pack.mypage.service;

import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.inquery.service.InqueryService;
import pack.newbook.service.NewBookService;
import pack.oldbook.service.OldBookService;
import pack.orderinfo.service.OrderInfoService;
import pack.user.service.CardInfoService;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {

    private final InqueryService inqueryService;
    private final OrderInfoService orderInfoService;
    private final OldBookService oldBookService;
    private final NewBookService newBookService;
    private final CardInfoService cardInfoService;

    public ModelAndView list3(String userId) {
        return new ModelAndView("mypage/mypage", Map.of(
            "odbook", orderInfoService.findTop3OrderList(userId),
            "rtbook", oldBookService.rent3List(userId),
            "dnbook", oldBookService.selectOldBookTop3(userId),
            "randNewbook", newBookService.recommandNewBook(),
            "cdinfo", cardInfoService.getCardList(userId),
            "inqinfo", inqueryService.selectInqList(userId)
        ));
    }

}
