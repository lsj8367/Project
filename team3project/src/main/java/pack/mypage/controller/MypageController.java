package pack.mypage.controller;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.*;
import pack.mypage.model.MypageImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import pack.repository.CardInfoRepository;

@Controller
@RequiredArgsConstructor
public class MypageController {
	private final MypageImpl mypageImpl;
	private final CardInfoRepository cardInfoRepository;

	@RequestMapping("mypage")
	public ModelAndView list3(HttpSession session) {
		String user_id =(String)session.getAttribute("id");
		
		ModelAndView modelAndView=new ModelAndView();
		//최근 주문 내역 3 모델앤뷰
		List<OrderInfoDto> orderlist = mypageImpl.orderlist(user_id);
		//최근 대여 내역 3 모델앤뷰
		List<OldBookDto> rentlist = mypageImpl.rentlist(user_id);
		//최근 도서 기부 내역 3 모델앤뷰
		List<OldBookDto> donorlist = mypageImpl.donorlist(user_id);
		//랜덤 새책 추천
		NewBookDto randNewbook = mypageImpl.recommandNewBook();
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