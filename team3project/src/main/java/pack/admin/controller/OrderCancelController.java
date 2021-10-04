package pack.admin.controller;

import java.util.List;
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
import pack.orderinfo.model.OrderInfoDto;

@Controller
@RequiredArgsConstructor
public class OrderCancelController {
    private final AdminDao adminDao;
    private final AdminService adminService;

    @GetMapping("ordercancel")
    public ModelAndView delayDeposit(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<OrderInfoDto> olist = adminDao.selectdelaydeposit();
        view.addObject("delay", olist);
        view.setViewName("admin/delaydeposit");

        return view;
    }

    @PostMapping("delorder")
    public String DelUser(HttpSession session, ModelMap model, NewBookDto newBookDto,
                          @RequestParam(name = "orderlist_no") String[] orderlist_no,
                          @RequestParam(name = "order_bookno") int[] order_bookno,
                          @RequestParam(name = "order_scount") int[] order_scount) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean isOrderDeleted = false;
        boolean isRollBackStock = false;

        for (int i = 0; i < orderlist_no.length; i++) {
            isOrderDeleted = adminDao.rmorder(orderlist_no[i]);
            newBookDto.setNb_no(order_bookno[i]);
            newBookDto.setNb_stock(order_scount[i]);
            isRollBackStock = adminService.rollBackStock(order_bookno[i], order_scount[i]);
        }
        if (isOrderDeleted && isRollBackStock) {
            return "redirect:/ordercancel";
        }

        return "redirect:/admin";
    }
}
