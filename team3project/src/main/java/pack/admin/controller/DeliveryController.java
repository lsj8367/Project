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
import pack.admin.service.AdminService;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final AdminService adminService;
    private final OrderInfoService orderInfoService;

    @GetMapping("delivery")
    public ModelAndView getOrderlist(HttpSession session) {
        String adminId = (String) session.getAttribute("admin_id");
        if (Objects.isNull(adminId) || adminId.equals("")) {
            return new ModelAndView("admin/admin_login");
        }
        return new ModelAndView("admin/deliveryinfo", Map.of(
            "info", adminService.selectAdminData(adminId),
            "olist", orderInfoService.notNoBankAll()
        ));
    }

    @PostMapping("deliveryok")
    public String upOrderState(@RequestParam(name = "orderlist_no") String[] orderlistNo,
        @RequestParam(name = "order_state") String[] orderState,
        HttpSession session, ModelMap model) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        for (int i = 0; i < orderlistNo.length; i++) {
            orderInfoService.updateOrderState(Integer.parseInt(orderlistNo[i]), orderState[i]);
        }

        return "redirect:/delivery";
    }

}
