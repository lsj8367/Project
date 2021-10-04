package pack.mypage.service;

import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
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
    private final MypageImpl mypageImpl;

    public ModelAndView list3(String user_id) {
        return new ModelAndView("mypage", Map.of(
            "odbook", mypageImpl.orderlist(user_id),
            "rtbook", mypageImpl.rentlist(user_id),
            "dnbook", oldBookRepository.findTop3ByObDonorOrderByObNameDesc(user_id),
            "randNewbook", newBookRepository.recommandNewBook(),
            "cdinfo", cardInfoRepository.cardList(user_id),
            "inqinfo", mypageImpl.inqlist(user_id)
        ));
    }

}
