package pack.mypage.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.OldBook;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.mypage.model.MyrentImpl;
import pack.mypage.service.MyRentService;
import pack.mypage.utils.DelayState;

@Controller
@RequiredArgsConstructor
public class MyrentController {

    private final MyrentImpl myRentImpl;
    private final MyRentService myRentService;

    @RequestMapping(value = "myrent", method = RequestMethod.GET)
    public ModelAndView myrentlist(HttpSession session) {
        String user_id = (String) session.getAttribute("id");
        ModelAndView modelAndView = new ModelAndView();

        //전체 대여 내역 모델앤뷰
        List<OldBookDto> rentlistall = myRentImpl.rentlistall(user_id);
        modelAndView.setViewName("mypage/myrent");
        modelAndView.addObject("rtbook", rentlistall);
        //System.out.println(rentlistall);

        return modelAndView;
    }

    @RequestMapping(value = "extendedate")
    public String rentex(@RequestParam(name = "rentno") int rent_no, HttpSession session) {
        String user_id = (String) session.getAttribute("id");

        boolean b = false;

        b = myRentImpl.updateState(rent_no);

        if (b) {
            System.out.println("성공");
            return "redirect:/myrent";
        } else {
            System.out.println(rent_no);
            System.out.println("실패");
            return "redirect:/mypage";
        }

    }

    @RequestMapping(value = "returnrbook")
    public String returnrbook(@RequestParam(name = "rentno") int rent_no, @RequestParam(name = "delaydate") int delaydate, UserDto ubean, HttpSession session) {
        String user_id = (String) session.getAttribute("id");

        OldBook oldBook = myRentService.getObPrice(rent_no);

        int price = (int) oldBook.getObPrice();
        DelayState delayState = DelayState.of(delaydate);
        ubean.setDelpoint(delayState.delPointCalculate(price));
        ubean.setUser_id(user_id);

        if (myRentImpl.deleteRentinf(rent_no)) {
            myRentService.upObProcess((long) rent_no);

            if (myRentImpl.delpointuser(ubean)) {
                return "redirect:/myrent";
            }
        }
        return "redirect:/mypage";
    }

}
