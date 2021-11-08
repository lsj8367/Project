package pack.web;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.mypage.model.MyrentImpl;
import pack.mypage.service.MyRentService;
import pack.mypage.utils.DelayState;
import pack.oldbook.domain.OldBook;
import pack.rentinfo.service.RentInfoService;
import pack.user.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyrentController {

    private final MyrentImpl myRentImpl;
    private final MyRentService myRentService;
    private final UserService userService;
    private final RentInfoService rentInfoService;

    @ExceptionHandler
    public ModelAndView error(Exception e) {
        log.error(e.getMessage());
        return new ModelAndView("redirect:/mypage");
    }

    @RequestMapping(value = "myrent", method = RequestMethod.GET)
    public ModelAndView myrentlist(HttpSession session) {
        String userId = (String) session.getAttribute("id");
        return new ModelAndView("mypage/myrent", Map.of(
            "rtbook", myRentImpl.rentlistall(userId)
        ));
    }

    @RequestMapping(value = "extendedate")
    public String rentex(@RequestParam(name = "rentno") int rentNo) {
        rentInfoService.updateState(rentNo);
        return "redirect:/myrent";
    }

    @RequestMapping(value = "returnrbook")
    public String returnrbook(@RequestParam(name = "rentno") int rentNo, @RequestParam(name = "delaydate") int delaydate, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        OldBook oldBook = myRentService.getObPrice(rentNo);
        DelayState delayState = DelayState.of(delaydate);

        rentInfoService.delete(rentNo);
        myRentService.upObProcess((long) rentNo);
        userService.minusRentPoint(userId, delayState.delPointCalculate((int) oldBook.getObPrice()));
        return "redirect:/myrent";
    }

}
