package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.controller.OrderInfoBean;
import pack.model.AdminDto;
import pack.model.OrderInfoDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NobankController {
    private final AdminInter adminInter;

    @RequestMapping(value = "nobankbookadmit", method = RequestMethod.GET)
    public ModelAndView getNobank(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);
        List<OrderInfoDto> ordernobank = adminInter.getNobank();
        view.addObject("nobank", ordernobank);
        view.setViewName("admin/nobanklist");

        return view;
    }

    @RequestMapping(value = "orderok", method = RequestMethod.POST)
    public String upOrderState(OrderInfoBean bean,
                               @RequestParam(name = "orderlist_no") String[] orderlist_no,
                               @RequestParam(name = "order_state") String[] order_state,
                               HttpSession session, ModelMap model) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = false;

        for (int i = 0; i < orderlist_no.length; i++) {
            bean.setOrderlist_no(orderlist_no[i]);
            bean.setOrder_state(order_state[i]);
            b = adminInter.updateOrderState(bean);
        }
        if (b) {
            return "redirect:/nobankbookadmit";
        }
        return "redirect:/adminmain";
    }
}
