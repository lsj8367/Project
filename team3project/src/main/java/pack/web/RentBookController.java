package pack.web;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.service.OldBookService;
import pack.oldbook.service.RentBookService;
import pack.rentinfo.service.RentInfoService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class RentBookController {

    private final OldBookService oldBookService;
    private final RentBookService rentBookService;
    private final UserService userService;
    private final RentInfoService rentInfoService;

    @RequestMapping("rentbooklist")
    public String cart() {
        return "rentbooklist";
    }

    // 중고책 대여
    @PostMapping(value = "Rental")
    public ModelAndView rentbook(HttpSession session, @RequestParam("ob_no") String rentNo) {
        //대여하기
        String userId = (String) session.getAttribute("id");
        rentInfoService.insertRentInfo(userId);
        // 중고책 대여 중으로 바꾸기
        oldBookService.updateRentOldBook(rentNo);

        //유저 포인트 차감
        userService.minusRentPoint(userId, 1000);

        return new ModelAndView("rentbook",
            Map.of(
                "rentBook", rentBookService.rentalInfo(rentNo),
                "rentUser", userService.selectUser(userId),
                "rentInfo", oldBookService.getRentInfo(userId)
            )
        );
    }

}
