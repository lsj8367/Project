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
import pack.model.AdminDto;
import pack.model.OrderInfoDto;

@Controller
@RequiredArgsConstructor
public class NobankController {
    private final AdminDao adminDao;

    @GetMapping("nobankbookadmit")
    public ModelAndView getNobank(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);
        List<OrderInfoDto> ordernobank = adminDao.getNobank();
        view.addObject("nobank", ordernobank);
        view.setViewName("admin/nobanklist");

        return view;
    }

    @PostMapping(value = "orderok")
    public String upOrderState(OrderInfoDto orderInfoDto,
                               @RequestParam(name = "orderlist_no") String[] orderlist_no,
                               @RequestParam(name = "order_state") String[] order_state,
                               HttpSession session, ModelMap model) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = false;

        for (int i = 0; i < orderlist_no.length; i++) {
            orderInfoDto.setOrderlist_no(orderlist_no[i]);
            orderInfoDto.setOrder_state(order_state[i]);
            b = adminDao.updateOrderState(orderInfoDto);
        }
        if (b) {
            return "redirect:/nobankbookadmit";
        }
        return "redirect:/adminmain";
    }
}
