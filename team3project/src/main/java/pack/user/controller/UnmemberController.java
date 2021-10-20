package pack.user.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.orderinfo.model.OrderInfoDto;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class UnmemberController {

    private final OrderInfoService orderInfoService;

    @GetMapping(value = "unmembercheck")
    public String check() {
        return "unmembercheck";
    }

    @PostMapping(value = "unmember")
    public ModelAndView search(@RequestParam("orderlist_no") String orderlistNo,
        @RequestParam("order_passwd") String orderPasswd, OrderInfoDto orderInfoDto) {

        if (orderlistNo.equals(orderInfoDto.getOrderlist_no()) && orderPasswd.equals(
            orderInfoDto.getOrder_passwd())) {
            return new ModelAndView("unmember", Map.of(
                "list", orderInfoService.findByUnmemberOrder(orderlistNo, orderPasswd),
                "order_passwd", orderPasswd
            ));
        }

        return new ModelAndView("error");
    }

}
