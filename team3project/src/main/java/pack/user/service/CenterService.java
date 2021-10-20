package pack.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.faqboard.domain.FaqBoard;
import pack.faqboard.repository.FaqBoardRepository;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final FaqBoardRepository faqBoardRepository;

    public List<Map<String, String>> qnaAll() {
        List<Map<String, String>> dataList = new ArrayList<>();
        List<FaqBoard> qnaList = faqBoardRepository.findAll();

        for (FaqBoard faqBoard : qnaList) {
            dataList.add(Map.of(
                "faq_no", String.valueOf(faqBoard.getFaqNo()),
                "faq_title", faqBoard.getFaqTitle(),
                "faq_content", faqBoard.getFaqContent(),
                "faq_date", faqBoard.getFaqDate(),
                "faq_type", faqBoard.getFaqType()
            ));
        }
        return dataList;
    }

    public List<Map<String, String>> faqDetail(String faqNo) {
        List<Map<String, String>> dataList = new ArrayList<>();
        Optional<FaqBoard> optionalFaqBoard = faqBoardRepository.findById(Long.parseLong(faqNo));

        optionalFaqBoard.ifPresent(faqBoard -> {
            dataList.add(Map.of(
                "faq_no", faqNo,
                "faq_title", faqBoard.getFaqTitle(),
                "faq_content", faqBoard.getFaqContent(),
                "faq_date", faqBoard.getFaqDate(),
                "faq_type", faqBoard.getFaqType()
            ));
        });
        return dataList;
    }

    public List<Map<String, String>> qnaOrder(String faq_type) {
        List<Map<String, String>> dataList = new ArrayList<>();

        List<FaqBoard> qnaList = faqBoardRepository.findAllByFaqType(faq_type);

        for (FaqBoard faqBoard : qnaList) {
            dataList.add(Map.of(
                "faq_no", String.valueOf(faqBoard.getFaqNo()),
                "faq_title", faqBoard.getFaqTitle(),
                "faq_content", faqBoard.getFaqContent(),
                "faq_date", faqBoard.getFaqDate(),
                "faq_type", faqBoard.getFaqType()
            ));
        }

        return dataList;
    }
}
