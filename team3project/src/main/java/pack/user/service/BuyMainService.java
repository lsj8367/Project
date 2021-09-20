package pack.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.repository.NewBookRepository;
import pack.user.model.NewBookListDao;

@Service
@RequiredArgsConstructor
public class BuyMainService {
    private final NewBookListDao newBookListDao;
    private final NewBookRepository newBookRepository;

    public ModelAndView buyMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bestseller", newBookRepository.selectBest());

        modelAndView.addObject("readtop3", newBookListDao.selectReadTop3());
        modelAndView.addObject("random10", newBookListDao.selectRandom10());
        return modelAndView;
    }

}
