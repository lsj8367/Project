package pack.web;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.mypage.service.MyRentService;
import pack.mypage.utils.DelayState;
import pack.oldbook.domain.OldBook;
import pack.oldbook.service.OldBookService;
import pack.rentinfo.service.RentInfoService;
import pack.user.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyrentController {

    private final MyRentService myRentService;
    private final UserService userService;
    private final OldBookService oldBookService;
    private final RentInfoService rentInfoService;

    @ExceptionHandler
    public ModelAndView error(Exception e) {
        log.error(e.getMessage());
        return new ModelAndView("redirect:/mypage");
    }

    @GetMapping(value = "myrent")
    public ModelAndView myrentlist(HttpSession session) {
        final String userId = (String) session.getAttribute("id");
        return new ModelAndView("mypage/myrent", Map.of(
            "rtbook", oldBookService.rentListAll(userId)
        ));
    }

    @RequestMapping(value = "extendedate")
    public String rentex(@RequestParam(name = "rentno") int rentNo) {
        rentInfoService.updateState(rentNo);
        return "redirect:/myrent";
    }

    @RequestMapping(value = "returnrbook")
    public String returnrbook(@RequestParam(name = "rentno") int rentNo, @RequestParam(name = "delaydate") int delaydate, HttpSession session) {
        final String userId = (String) session.getAttribute("id");
        final OldBook oldBook = myRentService.getObPrice(rentNo);
        final DelayState delayState = DelayState.of(delaydate);

        rentInfoService.delete(rentNo);
        myRentService.upObProcess((long) rentNo);
        userService.minusRentPoint(userId, delayState.delPointCalculate((int) oldBook.getObPrice()));
        return "redirect:/myrent";
    }

}
