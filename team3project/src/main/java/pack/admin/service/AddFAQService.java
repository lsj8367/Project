package pack.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.FaqBoard;
import pack.repository.FaqBoardRepository;

@Service
@RequiredArgsConstructor
public class AddFAQService {
    private final FaqBoardRepository faqBoardRepository;
    private final AdminService adminService;

    public ModelAndView getAdminLoginInfo(ModelAndView view, String adminId) {
        view.addObject("info", adminService.selectAdminData(adminId));
        view.setViewName("admin/addfaq");
        return view;
    }

    public ModelAndView insertFaqAfterLogin(ModelAndView view, FaqBoard faqBoard, String adminId) {
        view.addObject("info", adminService.selectAdminData(adminId));

        try {
            faqBoardRepository.save(faqBoard);
            view.setViewName("redirect:admin/addfaq");
        } catch (Exception e) {
            view.setViewName("error");
        }

        return view;
    }

}
