package pack.user.controller;

import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.RentInfoDto;
import pack.user.model.OldBookDao;
import pack.user.model.RentInfoDao;
import pack.user.model.UserDao;
import pack.user.service.RentBookService;

@Controller
@RequiredArgsConstructor
public class RentBookController {

    private final OldBookDao oldImpl;
    private final UserDao userDao;
    private final RentInfoDao rentImpl;
    private final RentBookService rentBookService;

    @RequestMapping("rentbooklist")
    public String cart() {
        return "rentbooklist";
    }

    // 중고책 대여
    @RequestMapping(value = "Rental", method = RequestMethod.POST)
    public ModelAndView rentbook(HttpSession session, @RequestParam("ob_no") String rent_no) {
        //대여하기
        String user_id = (String) session.getAttribute("id");
        String sdate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

        //대여정보에 삽입
        RentInfoDto rentInfoDto = new RentInfoDto();
        rentInfoDto.setRent_id(user_id);
        rentInfoDto.setRent_sdate(sdate);
        rentInfoDto.setRent_no(Integer.parseInt(rent_no));//중고책 번호
        rentImpl.rentOldBook(rentInfoDto);

        // 중고책 대여 중으로 바꾸기
        oldImpl.updateRentOldBook(rent_no);

        //유저 포인트 차감
        userDao.minusRentPoint(user_id);

        return new ModelAndView("rentbook",
            Map.of(
                "rentBook", rentBookService.rentalInfo(rent_no),
                "rentUser", userDao.selectUser(user_id),
                "rentInfo", rentImpl.getRentInfo(user_id)
            )
        );
    }
}
