package pack.web;

import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.service.OldBookService;
import pack.oldbook.service.RentBookService;
import pack.rentinfo.model.RentInfoDto;
import pack.user.model.RentInfoDao;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class RentBookController {

    private final OldBookService oldBookService;
    private final RentInfoDao rentImpl;
    private final RentBookService rentBookService;
    private final UserService userService;

    @RequestMapping("rentbooklist")
    public String cart() {
        return "rentbooklist";
    }

    // 중고책 대여
    @RequestMapping(value = "Rental", method = RequestMethod.POST)
    public ModelAndView rentbook(HttpSession session, @RequestParam("ob_no") String rent_no) {
        //대여하기
        String userId = (String) session.getAttribute("id");
        String sDate = new SimpleDateFormat("yyyyMMdd").format(System.nanoTime());

        //대여정보에 삽입
        RentInfoDto rentInfoDto = new RentInfoDto();
        rentInfoDto.setRent_id(userId);
        rentInfoDto.setRent_sdate(sDate);
        rentInfoDto.setRent_no(Integer.parseInt(rent_no));//중고책 번호
        rentImpl.rentOldBook(rentInfoDto);

        // 중고책 대여 중으로 바꾸기
        oldBookService.updateRentOldBook(rent_no);

        //유저 포인트 차감
        userService.minusRentPoint(userId, 1000);

        return new ModelAndView("rentbook",
            Map.of(
                "rentBook", rentBookService.rentalInfo(rent_no),
                "rentUser", userService.selectUser(userId),
                "rentInfo", rentImpl.getRentInfo(userId)
            )
        );
    }

}
