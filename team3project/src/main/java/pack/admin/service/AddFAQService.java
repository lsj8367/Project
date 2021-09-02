package pack.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.FaqBoardDto;

@Service
@RequiredArgsConstructor
public class AddFAQService {
    private final AdminDao adminDao;

    public ModelAndView getAdminLoginInfo(ModelAndView view, String adminId) {
        view.addObject("info", adminDao.getAdminLoginInfo(adminId));
        view.setViewName("admin/addfaq");
        return view;
    }

    public ModelAndView insertFaqAfterLogin(ModelAndView view, FaqBoardDto faqBoardDto, String adminId) {
        view.addObject("info", adminDao.getAdminLoginInfo(adminId));

        boolean b = adminDao.insertFaqData(faqBoardDto);
        if (b) {
            view.setViewName("redirect:admin/addfaq");
            return view;
        }

        view.setViewName("error");
        return view;
    }

}
