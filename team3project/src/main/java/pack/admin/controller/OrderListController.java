package pack.admin.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;
import pack.orderinfo.service.OrderInfoService;

@Controller
@RequiredArgsConstructor
public class OrderListController {

    private final AdminService adminService;
    private final OrderInfoService orderInfoService;

    @GetMapping(value = "orderlist")
    public ModelAndView goOrder(HttpSession session) {
        String adminId = (String) session.getAttribute("admin_id");
        if (adminId == null | adminId == "") {
            return new ModelAndView("admin/admin_login");
        }
        return new ModelAndView("admin/orderinfo", Map.of(
            "info", adminService.selectAdminData(adminId),
            "nborderlist", orderInfoService.selectNewBookOrderAll(),
            "oborderlist", orderInfoService.selectOldBookOrderAll()
        ));
    }

}
