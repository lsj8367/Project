package pack.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pack.newbook.repository.NewBookRepository;

@Service
@RequiredArgsConstructor
public class BuyMainService {
    private static final int LIMIT_NUMBER = 10;
    private final NewBookRepository newBookRepository;

    public ModelAndView buyMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bestseller", newBookRepository.selectBest());
        modelAndView.addObject("readtop3", newBookRepository.selectReadTop3());
        modelAndView.addObject("random10", newBookRepository.selectRandom(LIMIT_NUMBER));
        return modelAndView;
    }

}
