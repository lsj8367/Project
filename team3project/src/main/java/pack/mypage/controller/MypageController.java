package pack.mypage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.*;
import pack.mypage.model.MypageImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {
	private final MypageImpl mypageImpl;

	@RequestMapping("mypage")
	public ModelAndView list3(HttpSession session) {
		String user_id =(String)session.getAttribute("id");
		
		ModelAndView modelAndView=new ModelAndView();
		
		
		//최근 주문 내역 3 모델앤뷰
		List<OrderInfoDto> orderlist = mypageImpl.orderlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("odbook", orderlist);
		
		//최근 대여 내역 3 모델앤뷰
		List<OldBookDto> rentlist = mypageImpl.rentlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("rtbook", rentlist);
		
		//최근 도서 기부 내역 3 모델앤뷰
		List<OldBookDto> donorlist = mypageImpl.donorlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("dnbook", donorlist);
		
		//랜덤 새책 추천
		NewBookDto randNewbook = mypageImpl.recommandNewBook();
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("randNewbook", randNewbook);
		
		
		//최근 카드정보 내역 3 모델앤뷰
		List<CardInfoDto> cardlist = mypageImpl.cardlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("cdinfo", cardlist);
		
		//최근 문의 내역 3 모델앤뷰
		List<InqueryDto> inqlist = mypageImpl.inqlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("inqinfo", inqlist);
	 
		return modelAndView;
	}
}