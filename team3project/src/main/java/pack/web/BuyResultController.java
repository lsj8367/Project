package pack.web;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.domain.OldBook;
import pack.oldbook.service.OldBookService;
import pack.orderinfo.service.OrderInfoService;
import pack.user.domain.User;
import pack.user.model.UserDto;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class BuyResultController {

    private final OldBookService oldBookService;
    private final UserService userService;
    private final OrderInfoService orderInfoService;

    @PostMapping("buyresult")
    public ModelAndView result(HttpSession session, HttpServletRequest request, @RequestParam("radioPaytype") String radioPaytype) {
        String orderId = request.getParameter("order_id"); //아이디
        String orderPerson = request.getParameter("order_person"); //성명
        String obNo = request.getParameter("ob_no");
        String userPasswd = request.getParameter("order_password");
        String userAddress = request.getParameter("order_address");
        String orderSum = request.getParameter("order_sum");

        radioPaytype = payTypeSelection(radioPaytype);

        OldBook oldBook = oldBookService.view(obNo);

        if (Objects.isNull(orderId) || Objects.isNull(userPasswd)) {
            orderId = "";
            userPasswd = " ";
        }

        orderInfoService.order(session, Map.of(
                "orderId", orderId,
                "orderPerson", orderPerson,
                "orderSum", Integer.parseInt(orderSum),
                "payType", radioPaytype,
                "userPasswd", userPasswd,
                "address", userAddress,
                "obNo", oldBook.getObNo().intValue()
            )
        );

        if (session.getAttribute("id") != null && request.getParameter("writepoint") != "") { // 회원인경우
            UserDto userDto = new UserDto();
            int writepoint = Integer.parseInt(request.getParameter("writepoint"));

            userDto.setUser_id((String) session.getAttribute("id"));
            userDto.setUser_point(writepoint);

            userService.usePoint(userDto);

            User user = userService.selectUser(orderId);
            session.setAttribute("point", user.getUserPoint());
        }

        oldBookService.updateOldBook(Integer.parseInt(obNo));

        return new ModelAndView("buyresult", Map.of(
            "buylist", orderInfoService.findByPerson(orderPerson)
        ));
    }

    private String payTypeSelection(String radioPaytype) {
        if (radioPaytype.equals("무통장입금")) {
            return "0";
        }
        return "1";
    }

}
