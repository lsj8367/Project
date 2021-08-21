package pack.mypage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.controller.UserBean;
import pack.model.OldBookDto;
import pack.mypage.model.MyrentImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyrentController {
	private final MyrentImpl myRentImpl;
	
	@RequestMapping(value="myrent", method=RequestMethod.GET)
	public ModelAndView myrentlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView=new ModelAndView();
		
		//전체 대여 내역 모델앤뷰
		List<OldBookDto> rentlistall = myRentImpl.rentlistall(user_id);
		modelAndView.setViewName("mypage/myrent");
		modelAndView.addObject("rtbook", rentlistall);
		//System.out.println(rentlistall);
		
		return modelAndView;
	}
	
	@RequestMapping(value="extendedate")
	public String rentex(@RequestParam(name="rentno") int rent_no, HttpSession session) {
		String user_id = (String) session.getAttribute("id");

		boolean b = false;
		
		b = myRentImpl.updateState(rent_no);

		
		if(b) {
		System.out.println("성공");
			return "redirect:/myrent";
		}else {
		System.out.println(rent_no);
			System.out.println("실패");
			return "redirect:/mypage";
		}
		
	}
	
	@RequestMapping(value="returnrbook")
	public String returnrbook(@RequestParam(name="rentno") int rent_no,@RequestParam(name="delaydate") int delaydate, UserBean ubean, HttpSession session) {
		String user_id = (String) session.getAttribute("id");

		boolean a = false;
		boolean b = false;
		boolean c = false;
		
		OldBookDto dto = myRentImpl.getObPrice(rent_no);
		int price = dto.getOb_price();
		if(delaydate < 3) {
			ubean.setDelpoint(Integer.parseInt(String.valueOf(Math.round(price * 0.05))));
		}else if(delaydate < 6) {
			ubean.setDelpoint(Integer.parseInt(String.valueOf(Math.round(price * 0.1))));
		}else if(delaydate < 10) {
			ubean.setDelpoint(Integer.parseInt(String.valueOf(Math.round(price * 0.15))));
		}else if(delaydate < 14) {
			ubean.setDelpoint(Integer.parseInt(String.valueOf(Math.round(price * 0.2))));
		}else {
			ubean.setDelpoint(0);
		}
		ubean.setUser_id(user_id);
		b = myRentImpl.deleteRentinf(rent_no);
		if(b) {
			c = myRentImpl.upObProcess(rent_no);
			if(c)
			a = myRentImpl.delpointuser(ubean);
			if(a) {
				System.out.println("성공");
				return "redirect:/myrent";
			}
		}
		System.out.println("실패");
		return "redirect:/mypage";

		
	}
}
