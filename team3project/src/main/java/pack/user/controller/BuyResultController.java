package pack.user.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.service.OldBookService;
import pack.orderinfo.model.OrderInfoDto;
import pack.user.domain.User;
import pack.user.model.BuyDao;
import pack.user.model.BuyResultDao;
import pack.user.model.UserDto;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class BuyResultController {

    private final BuyResultDao buyResultDao;
    private final BuyDao buyDao;
    private final OldBookService oldBookService;
    private final UserService userService;

    @PostMapping("buyresult")
    public ModelAndView result(HttpSession session, HttpServletRequest request, @RequestParam("radioPaytype") String radioPaytype) {
        String orderId = request.getParameter("order_id"); //아이디
        String orderPerson = request.getParameter("order_person"); //성명
        String obNo = request.getParameter("ob_no");
        String userPasswd = request.getParameter("order_password");
        String userAddress = request.getParameter("order_address");

        radioPaytype = payTypeSelection(radioPaytype);
        String order_sum = request.getParameter("order_sum");

        OrderInfoDto dto = buyResultDao.order(session, orderId, orderPerson, Integer.parseInt(order_sum), radioPaytype, userPasswd, userAddress, obNo);

        if (session.getAttribute("id") != null && request.getParameter("writepoint") != "") { // 회원인경우
            UserDto userDto = new UserDto();
            int writepoint = Integer.parseInt(request.getParameter("writepoint"));

            userDto.setUser_id((String) session.getAttribute("id"));
            userDto.setUser_point(writepoint);

            userService.usePoint(userDto);

            User user = userService.selectUser(orderId);
            session.setAttribute("point", user.getUserPoint());
        }

        if (Objects.isNull(dto)) {
            return new ModelAndView("error");
        }

        oldBookService.updateOldBook(Integer.parseInt(obNo));

        return new ModelAndView("buyresult", Map.of(
            "buylist", buyDao.show(orderPerson)
        ));
    }

    private String payTypeSelection(String radioPaytype) {
        if (radioPaytype.equals("무통장입금")) {
            return "0";
        }
        return "1";
    }

}
