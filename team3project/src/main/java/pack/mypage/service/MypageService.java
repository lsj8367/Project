package pack.mypage.service;

import com.querydsl.core.Tuple;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.NewBook;
import pack.model.InqueryDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.mypage.model.MypageImpl;
import pack.repository.CardInfoRepository;
import pack.repository.NewBookRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {
    private final CardInfoRepository cardInfoRepository;
    private final NewBookRepository newBookRepository;
    private final MypageImpl mypageImpl;

    public ModelAndView list3(String user_id) {
        ModelAndView modelAndView = new ModelAndView();

        //최근 주문 내역 3 모델앤뷰
        List<OrderInfoDto> orderlist = mypageImpl.orderlist(user_id);

        //최근 대여 내역 3 모델앤뷰
        List<OldBookDto> rentlist = mypageImpl.rentlist(user_id);

        //최근 도서 기부 내역 3 모델앤뷰
        List<OldBookDto> donorlist = mypageImpl.donorlist(user_id);

        //랜덤 새책 추천
        NewBook randNewbook = newBookRepository.recommandNewBook();

        //최근 카드정보 내역 3 모델앤뷰
        List<Tuple> cardList = cardInfoRepository.cardList(user_id);

        //최근 문의 내역 3 모델앤뷰
        List<InqueryDto> inqlist = mypageImpl.inqlist(user_id);

        modelAndView.addObject("odbook", orderlist);
        modelAndView.addObject("rtbook", rentlist);
        modelAndView.addObject("dnbook", donorlist);
        modelAndView.addObject("randNewbook", randNewbook);
        modelAndView.addObject("cdinfo", cardList);
        modelAndView.addObject("inqinfo", inqlist);

        return modelAndView;
    }

}
