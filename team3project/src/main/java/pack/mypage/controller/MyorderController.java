package pack.mypage.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.mypage.model.MyorderImpl;
import pack.mypage.service.MyOrderService;
import pack.orderinfo.model.OrderInfoDto;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class MyorderController {
    private static final String NEW = "새책";
    private static final String RENT = "중고책";

    private final MyorderImpl myorderImpl;
    private final MyOrderService myOrderService;
    private final OrderInfoService orderInfoService;

    @RequestMapping("myorder")
    public ModelAndView myorderlist(HttpSession session) {
        return new ModelAndView("mypage/myorder", Map.of(
            "odbook", myorderImpl.orderlistall((String) session.getAttribute("id")),
            "randNewbook", myOrderService.recommandNewBook()
        ));
    }

    @RequestMapping("myoldorder")
    public ModelAndView myorderoldlist(HttpSession session) {
        return new ModelAndView("mypage/mytypeorder", Map.of(
            "odbook", myorderImpl.orderoldlistall((String) session.getAttribute("id")),
            "booktype", RENT,
            "randNewbook", myOrderService.recommandNewBook()
        ));
    }

    @RequestMapping("myneworder")
    public ModelAndView myneworderlist(HttpSession session) {
        return new ModelAndView("", Map.of(
            "odbook", myorderImpl.ordernewlistall((String) session.getAttribute("id")),
            "booktype", NEW,
            "randNewbook", myOrderService.recommandNewBook()
        ));
    }

    @RequestMapping("orderinfocheck")
    public ModelAndView myorderinfoAll(@RequestParam("orderlist_no") String orderlistNo) {
        return new ModelAndView("mypage/orderinfocheck", Map.of(
            "odinfo", orderInfoService.findByOrderInfo(orderlistNo),
            "odinfoall", orderInfoService.myOrderInfoAll(orderlistNo),
            "randNewbook", myOrderService.recommandNewBook()
        ));
    }

    @RequestMapping("myorderup")
    public ModelAndView myorderinfoUp(@RequestParam("orderlist_no") String orderlistNo) {
        return new ModelAndView("mypage/orderinfoup", Map.of(
            "odinfo", orderInfoService.findByOrderInfo(orderlistNo),
            "odinfoall", orderInfoService.myOrderInfoAll(orderlistNo),
            "randNewbook", myOrderService.recommandNewBook()
        ));
    }

    @RequestMapping(value = "myorderupok", method = RequestMethod.POST)
    public ModelAndView updatemyorderinfo(OrderInfoDto orderInfoDto) {
        orderInfoService.updateOrderInfo(orderInfoDto);
        return new ModelAndView("redirect:/myneworder");
    }


    @RequestMapping("deletemyorder")
    public ModelAndView deletemyorder(@RequestParam("no") int orderNo, @RequestParam("count") int order_scount, @RequestParam("bookno") int nbNo) {
        //주문 내역 삭제
        boolean b = myorderImpl.deletemyorder(orderNo);
        if (b) {
            myOrderService.updateScount(nbNo, order_scount);
            return new ModelAndView("redirect:/myneworder");
        }

        return new ModelAndView("error");
    }

}
