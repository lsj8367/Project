package pack.admin.controller;

import java.util.List;
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
import pack.model.RentInfoDto;
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class BestRentbooksetController {
   private final AdminDao adminDao;
   private final AdminService adminService;
   
   @GetMapping("bestrentbookset")
   public ModelAndView goBestRentbookset(HttpSession session, ModelMap model) {
      ModelAndView view = new ModelAndView();
      String admin_id = (String)session.getAttribute("admin_id");

      if(Objects.isNull(admin_id) || admin_id.equals("")) {
         view.setViewName("admin/admin_login");
         return view;
      }

      model.addAttribute("info", adminService.selectAdminData(admin_id));

      List<RentInfoDto> rentmonth = adminDao.mbRentMonth();
      view.addObject("rtm", rentmonth);
      view.setViewName("admin/bestrentbookset");

      return view;
   }
   
   @PostMapping("monthbestrent")
   public ModelAndView goBestReview(HttpSession session, ModelMap model, @RequestParam("sql") String sql) {
      ModelAndView view = new ModelAndView();

      String admin_id = (String)session.getAttribute("admin_id");
      if(admin_id == null | admin_id == "") 
         view.setViewName("admin/admin_login");
      else {
         model.addAttribute("info", adminService.selectAdminData(admin_id));
       
         List<RentInfoDto> rentmonth = adminDao.mbRentMonth();
         List<RentInfoDto> rtl = adminDao.mbEstRent(sql);
         view.addObject("rtm", rentmonth);
         view.addObject("rtl", rtl);
         view.setViewName("admin/bestrentbookset");
      }
   
      return view;
   }
   
   @PostMapping("givepoint2")
   public String JikwonUpJik(HttpSession session, ModelMap model, UserDto bean,
                     @RequestParam(name="rn") int[] rank,
                     @RequestParam(name="ob_userid") String[] userid){

      String admin_id = (String)session.getAttribute("admin_id");

      if(admin_id == null | admin_id == "") 
         return "admin/admin_login";
      else {
         model.addAttribute("info", adminService.selectAdminData(admin_id));
      }
      
      
      boolean b = false;
      
      for (int i = 0; i < userid.length; i++) {
         if(rank[i] == 1) {
            bean.setPluspoint(3000);
         }else if(rank[i] == 2) {
            bean.setPluspoint(1500);
         }else if(rank[i] == 3) {
            bean.setPluspoint(500);
         }else {
            bean.setPluspoint(0);
         }
         bean.setUser_id(userid[i]);
         b = adminDao.upUserPoint(bean);
      }

      if(b) {
         return "redirect:/bestrentbookset";
      }

      return "redirect:/adminmain";
   }
   
}