package pack.mypage.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.NewBookDto;
import pack.model.OrderInfoDto;
import pack.mypage.model.MyorderImpl;

@Controller
@RequiredArgsConstructor
public class MyorderController {
	private final MyorderImpl myorderImpl;

	@RequestMapping("myorder")
	public ModelAndView myorderlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		// 전체 주문 내역 모델앤뷰
		List<OrderInfoDto> orderlistall = myorderImpl.orderlistall(user_id);
		modelAndView.setViewName("mypage/myorder");
		modelAndView.addObject("odbook", orderlistall);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myorderImpl.recommandNewBook();
		modelAndView.setViewName("mypage/myorder");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}

	@RequestMapping("myoldorder")
	public ModelAndView myorderoldlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		String booktype = "중고책";
		// 전체 주문 내역 모델앤뷰
		List<OrderInfoDto> orderoldlistall = myorderImpl.orderoldlistall(user_id);
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("odbook", orderoldlistall);
		modelAndView.addObject("booktype", booktype);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myorderImpl.recommandNewBook();
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}

	@RequestMapping("myneworder")
	public ModelAndView myneworderlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		String booktype = "새책";
		// 새책 전체 주문 내역 모델앤뷰
		List<OrderInfoDto> ordernewlistall = myorderImpl.ordernewlistall(user_id);
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("odbook", ordernewlistall);
		modelAndView.addObject("booktype", booktype);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myorderImpl.recommandNewBook();
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}

	@RequestMapping("orderinfocheck")
	public ModelAndView myorderinfoAll(HttpSession session, @RequestParam("no") String orderlist_no) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		// 주문번호별 주문확인 모델앤뷰
		OrderInfoDto myorderinfo = myorderImpl.myorderinfo(orderlist_no);
		List<OrderInfoDto> myorderinfoall = myorderImpl.myorderinfoall(orderlist_no);
		modelAndView.setViewName("mypage/orderinfocheck");
		modelAndView.addObject("odinfo", myorderinfo);
		modelAndView.addObject("odinfoall", myorderinfoall);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myorderImpl.recommandNewBook();
		modelAndView.setViewName("mypage/orderinfocheck");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}
	
	
	@RequestMapping("myorderup")
	public ModelAndView myorderinfoUp(HttpSession session, @RequestParam("no") String orderlist_no) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		// 주문번호별 주문확인 모델앤뷰
		OrderInfoDto myorderinfo = myorderImpl.myorderinfo(orderlist_no);
		List<OrderInfoDto> myorderinfoall = myorderImpl.myorderinfoall(orderlist_no);
		modelAndView.setViewName("mypage/orderinfocheck");
		modelAndView.addObject("odinfo", myorderinfo);
		modelAndView.addObject("odinfoall", myorderinfoall);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myorderImpl.recommandNewBook();
		modelAndView.setViewName("mypage/orderinfoup");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}
	
	@RequestMapping(value="myorderupok", method = RequestMethod.POST)
	public ModelAndView updatemyorderinfo(HttpSession session, OrderInfoDto orderInfoDto) {
		ModelAndView modelAndView = new ModelAndView();
		
		//주문 내역 삭제
		boolean b = myorderImpl.updateMyOrderInfo(orderInfoDto);
		if(b) {
			modelAndView.setViewName("redirect:/myneworder");
			return modelAndView;
		}

		modelAndView.setViewName("error");
		return modelAndView;
	}
	
	
	@RequestMapping("deletemyorder")
	public ModelAndView deletemyorder(HttpSession session, NewBookDto newBookDto, @RequestParam("no") int order_no, @RequestParam("count") int order_scount, @RequestParam("bookno") int nb_no) {
		ModelAndView modelAndView = new ModelAndView();
		
		//주문 내역 삭제
		boolean b = myorderImpl.deletemyorder(order_no);
		if(b) {
			newBookDto.setNb_scount(order_scount);
			newBookDto.setNb_no(nb_no);
			boolean c = myorderImpl.upNbScount(newBookDto);
			if(c)
			modelAndView.setViewName("redirect:/myneworder");
		}else {
			modelAndView.setViewName("error");
		}


		return modelAndView;
	}
	
}
