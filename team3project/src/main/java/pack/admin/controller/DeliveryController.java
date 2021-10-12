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
import pack.orderinfo.model.OrderInfoDto;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final OrderInfoService orderInfoService;

    @GetMapping("delivery")
    public ModelAndView getOrderlist(HttpSession session, ModelMap model) {
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
    public String upOrderState(OrderInfoDto orderInfoDto,
        @RequestParam(name = "orderlist_no") String[] orderlist_no,
        @RequestParam(name = "order_state") String[] order_state,
        HttpSession session, ModelMap model) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < orderlist_no.length; i++) {
            orderInfoDto.setOrderlist_no(orderlist_no[i]);
            orderInfoDto.setOrder_state(order_state[i]);
            b = adminDao.uporderstate(orderInfoDto);
        }

        if (b) {
            return "redirect:/delivery";
        }

        return "redirect:/adminmain";
    }

}
