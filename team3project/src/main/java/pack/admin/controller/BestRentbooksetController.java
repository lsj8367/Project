package pack.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.admin.model.AdminInter;
import pack.controller.UserBean;
import pack.model.AdminDto;
import pack.model.RentInfoDto;

@Controller
public class BestRentbooksetController {

   @Autowired
   AdminInter adminInter;
   
   @RequestMapping(value="bestrentbookset", method=RequestMethod.GET)
   public ModelAndView goBestRentbookset(HttpSession session, ModelMap model) {
      ModelAndView view = new ModelAndView();
      
      String admin_id = (String)session.getAttribute("admin_id");
      if(admin_id == null | admin_id == "") 
         view.setViewName("admin/admin_login");
      else {
         AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
           model.addAttribute("info", dto);
           
           List<RentInfoDto> rentmonth = adminInter.getRentmonth();
           view.addObject("rtm", rentmonth);
           view.setViewName("admin/bestrentbookset");
      }
      
      return view;
   }
   
   @RequestMapping(value="monthbestrent", method=RequestMethod.POST)
   public ModelAndView goBestReview(HttpSession session, ModelMap model, @RequestParam("sql") String sql) {
      ModelAndView view = new ModelAndView();

      String admin_id = (String)session.getAttribute("admin_id");
      if(admin_id == null | admin_id == "") 
         view.setViewName("admin/admin_login");
      else {
         AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
         model.addAttribute("info", dto);
       
         List<RentInfoDto> rentmonth = adminInter.getRentmonth();
         List<RentInfoDto> rtl = adminInter.getBestRentmonth(sql);
         view.addObject("rtm", rentmonth);
         view.addObject("rtl", rtl);
         view.setViewName("admin/bestrentbookset");
      }
   
      return view;
   }
   
   @RequestMapping(value = "givepoint2", method = RequestMethod.POST)
   public String JikwonUpJik(HttpSession session, ModelMap model, UserBean bean,
                     @RequestParam(name="rn") int rank[], 
                     @RequestParam(name="ob_userid") String userid[]){
      String admin_id = (String)session.getAttribute("admin_id");
      if(admin_id == null | admin_id == "") 
         return "admin/admin_login";
      else {
         AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
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
         b = adminInter.upUserPoint(bean);
      }
      if(b) {
         System.out.println("성공");
         return "redirect:/bestrentbookset";
      }else {
         return "redirect:/adminmain";
      }
   }
   
}