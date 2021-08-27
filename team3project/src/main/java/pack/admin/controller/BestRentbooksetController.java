package pack.admin.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.RentInfoDto;
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class BestRentbooksetController {
   private final AdminDao adminDao;
   
   @RequestMapping(value="bestrentbookset", method=RequestMethod.GET)
   public ModelAndView goBestRentbookset(HttpSession session, ModelMap model) {
      ModelAndView view = new ModelAndView();
      String admin_id = (String)session.getAttribute("admin_id");

      if(Objects.isNull(admin_id)|| admin_id.equals("")) {
         view.setViewName("admin/admin_login");
         return view;
      }

      AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
      model.addAttribute("info", dto);

      List<RentInfoDto> rentmonth = adminDao.getRentmonth();
      view.addObject("rtm", rentmonth);
      view.setViewName("admin/bestrentbookset");

      return view;
   }
   
   @RequestMapping(value="monthbestrent", method=RequestMethod.POST)
   public ModelAndView goBestReview(HttpSession session, ModelMap model, @RequestParam("sql") String sql) {
      ModelAndView view = new ModelAndView();

      String admin_id = (String)session.getAttribute("admin_id");
      if(admin_id == null | admin_id == "") 
         view.setViewName("admin/admin_login");
      else {
         AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
         model.addAttribute("info", dto);
       
         List<RentInfoDto> rentmonth = adminDao.getRentmonth();
         List<RentInfoDto> rtl = adminDao.getBestRentmonth(sql);
         view.addObject("rtm", rentmonth);
         view.addObject("rtl", rtl);
         view.setViewName("admin/bestrentbookset");
      }
   
      return view;
   }
   
   @RequestMapping(value = "givepoint2", method = RequestMethod.POST)
   public String JikwonUpJik(HttpSession session, ModelMap model, UserDto bean,
                     @RequestParam(name="rn") int[] rank,
                     @RequestParam(name="ob_userid") String[] userid){

      String admin_id = (String)session.getAttribute("admin_id");

      if(admin_id == null | admin_id == "") 
         return "admin/admin_login";
      else {
         AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
         model.addAttribute("info", dto);
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