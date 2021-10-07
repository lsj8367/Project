package pack.mypage.service;

import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.inquery.service.InqueryService;
import pack.mypage.model.MypageImpl;
import pack.cardinfo.repository.CardInfoRepository;
import pack.newbook.repository.NewBookRepository;
import pack.oldbook.repository.OldBookRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {
    private final CardInfoRepository cardInfoRepository;
    private final NewBookRepository newBookRepository;
    private final OldBookRepository oldBookRepository;
    private final InqueryService inqueryService;
    private final MypageImpl mypageImpl;

    public ModelAndView list3(String userId) {
        return new ModelAndView("mypage/mypage", Map.of(
            "odbook", mypageImpl.orderlist(userId),
            "rtbook", mypageImpl.rentlist(userId),
            "dnbook", oldBookRepository.findTop3ByObDonorOrderByObNameDesc(userId),
            "randNewbook", newBookRepository.recommandNewBook(),
            "cdinfo", cardInfoRepository.cardList(userId),
            "inqinfo", inqueryService.selectInqList(userId)
        ));
    }

}
