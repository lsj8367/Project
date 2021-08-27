package pack.user.controller;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.OrderInfoDto;
import pack.model.UserDto;
import pack.user.model.BuyDao;
import pack.user.model.BuyResultDao;
import pack.user.model.OldBookDao;
import pack.user.model.UserDao;

@Controller
@RequiredArgsConstructor
public class BuyResultController {
	private final BuyResultDao buyResultDao;
	private final BuyDao buyDao;
	private final UserDao userDao;
	private final OldBookDao oldBookDao;


	@PostMapping("buyresult")
	public ModelAndView result(HttpSession session, HttpServletRequest request, @RequestParam("radioPaytype") String radioPaytype) {
		ModelAndView modelAndView = new ModelAndView();
		OrderInfoDto dto;
		OrderInfoDto dto2;

		String orderId = request.getParameter("order_id"); //아이디
		String orderPerson = request.getParameter("order_person"); //성명
		String obNo = request.getParameter("ob_no");
		String userPasswd = request.getParameter("order_password");
		String userAddress = request.getParameter("order_address");

		radioPaytype = payTypeSelection(radioPaytype);
		String order_sum = request.getParameter("order_sum");

		
		dto = buyResultDao.order(session, orderId, orderPerson, Integer.parseInt(order_sum), radioPaytype, userPasswd, userAddress, obNo);


		if(session.getAttribute("id") != null && request.getParameter("writepoint") != "") { // 회원인경우
			UserDto user = new UserDto();
			int writepoint = Integer.parseInt(request.getParameter("writepoint"));

			user.setUser_id((String)session.getAttribute("id"));
			user.setUser_point(writepoint);

			userDao.usePoint(user);
			UserDto user2 = userDao.selectUser(orderId);

			session.setAttribute("point", user2.getUser_point());
		}


		if(Objects.isNull(dto)) {
			modelAndView.setViewName("error");
			return modelAndView;
		}

		dto2 = buyDao.show(orderPerson);
		oldBookDao.update(Integer.parseInt(obNo));
		modelAndView.addObject("buylist", dto2);
		modelAndView.setViewName("buyresult");
		return modelAndView;
	}

	private String payTypeSelection(String radioPaytype) {
		if(radioPaytype.equals("무통장입금")) {
			return "0";
		}
		return "1";
	}

}
