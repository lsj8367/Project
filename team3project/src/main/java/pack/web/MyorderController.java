package pack.web;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.newbook.service.NewBookService;
import pack.orderinfo.model.OrderInfoDto;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class MyorderController {

    private static final String NEW = "새책";
    private static final String RENT = "중고책";

    private final OrderInfoService orderInfoService;
    private final NewBookService newBookService;

    @RequestMapping("myorder")
    public ModelAndView myorderlist(HttpSession session) {
        return new ModelAndView("mypage/myorder", Map.of(
            "odbook", orderInfoService.findOrderListAll((String) session.getAttribute("id")),
            "randNewbook", newBookService.recommandNewBook()
        ));
    }

    @RequestMapping("myoldorder")
    public ModelAndView myorderoldlist(HttpSession session) {
        return new ModelAndView("mypage/mytypeorder", Map.of(
            "odbook", orderInfoService.findOldBookOrderListAll((String) session.getAttribute("id")),
            "booktype", RENT,
            "randNewbook", newBookService.recommandNewBook()
        ));
    }

    @RequestMapping("myneworder")
    public ModelAndView myneworderlist(HttpSession session) {
        return new ModelAndView("mypage/mytypeorder", Map.of(
            "odbook", orderInfoService.findNewBookOrderListAll((String) session.getAttribute("id")),
            "booktype", NEW,
            "randNewbook", newBookService.recommandNewBook()
        ));
    }

    @RequestMapping("orderinfocheck")
    public ModelAndView myorderinfoAll(@RequestParam("orderlist_no") String orderlistNo) {
        return new ModelAndView("mypage/orderinfocheck", Map.of(
            "odinfo", orderInfoService.findByOrderInfo(orderlistNo),
            "odinfoall", orderInfoService.myOrderInfoAll(orderlistNo),
            "randNewbook", newBookService.recommandNewBook()
        ));
    }

    @RequestMapping("myorderup")
    public ModelAndView myorderinfoUp(@RequestParam("orderlist_no") String orderlistNo) {
        return new ModelAndView("mypage/orderinfoup", Map.of(
            "odinfo", orderInfoService.findByOrderInfo(orderlistNo),
            "odinfoall", orderInfoService.myOrderInfoAll(orderlistNo),
            "randNewbook", newBookService.recommandNewBook()
        ));
    }

    @RequestMapping(value = "myorderupok", method = RequestMethod.POST)
    public ModelAndView updatemyorderinfo(OrderInfoDto orderInfoDto) {
        orderInfoService.updateOrderInfo(orderInfoDto);
        return new ModelAndView("redirect:/myneworder");
    }


    @RequestMapping("deletemyorder")
    public ModelAndView deletemyorder(@RequestParam("no") int orderNo,
        @RequestParam("count") int order_scount, @RequestParam("bookno") int nbNo) {
        //주문 내역 삭제
        orderInfoService.deleteMyOrder(orderNo);
        newBookService.updateScount(nbNo, order_scount);
        return new ModelAndView("redirect:/myneworder");
    }

}
