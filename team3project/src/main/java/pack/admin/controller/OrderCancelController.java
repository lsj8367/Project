package pack.admin.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.newbook.model.NewBookDto;
import pack.orderinfo.model.dto.OrderResponseDto;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class OrderCancelController {
    private final AdminDao adminDao;
    private final AdminService adminService;
    private final OrderInfoService orderInfoService;

    @GetMapping("ordercancel")
    public ModelAndView delayDeposit(HttpSession session) {
        String adminId = (String) session.getAttribute("admin_id");
        if (Objects.isNull(adminId) || adminId.equals("")) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/delaydeposit", Map.of(
            "info", adminService.selectAdminData(adminId),
            "delay", OrderResponseDto.of(orderInfoService.selectDelayDeposit())
        ));
    }

    @PostMapping("delorder")
    public String DelUser(HttpSession session, ModelMap model, NewBookDto newBookDto,
                          @RequestParam(name = "orderlist_no") String[] orderlistNo,
                          @RequestParam(name = "order_bookno") int[] orderBookNo,
                          @RequestParam(name = "order_scount") int[] orderScount) {

        String adminId = (String) session.getAttribute("admin_id");
        if (Objects.isNull(adminId) || adminId.equals("")) {
            return "admin/admin_login";
        }
        model.addAttribute("info", adminService.selectAdminData(adminId));

        boolean isOrderDeleted = false;
        boolean isRollBackStock = false;

        for (int i = 0; i < orderlistNo.length; i++) {
            isOrderDeleted = adminDao.rmorder(orderlistNo[i]);
            newBookDto.setNb_no(orderBookNo[i]);
            newBookDto.setNb_stock(orderScount[i]);
            isRollBackStock = adminService.rollBackStock(orderBookNo[i], orderScount[i]);
        }
        if (isOrderDeleted && isRollBackStock) {
            return "redirect:/ordercancel";
        }

        return "redirect:/admin";
    }
}
