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
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        Map<String, String> data = new HashMap<>();
        List<FaqBoard> qnaList = faqBoardRepository.findAll();

        for (FaqBoard faqBoard : qnaList) {
            data.put("faq_no", String.valueOf(faqBoard.getFaqNo()));
            data.put("faq_date", faqBoard.getFaqDate());
            data.put("faq_title", faqBoard.getFaqTitle());
            data.put("faq_cont", faqBoard.getFaqContent());
            data.put("faq_type", faqBoard.getFaqType());
            dataList.add(data);
        }
        return dataList;
    }

    public List<Map<String, String>> faqDetail(String faqNo) {
        List<Map<String, String>> dataList = new ArrayList<>();
        Map<String, String> data = new HashMap<>();
        Optional<FaqBoard> optionalFaqBoard = faqBoardRepository.findById(Long.parseLong(faqNo));

        optionalFaqBoard.ifPresent(faqBoard -> {
            data.put("faq_no", faqNo);
            data.put("faq_title", faqBoard.getFaqTitle());
            data.put("faq_content", faqBoard.getFaqContent());
            data.put("faq_date", faqBoard.getFaqDate());
            data.put("faq_type", faqBoard.getFaqType());
        });

        dataList.add(data);
        return dataList;
    }

    public List<Map<String, String>> qnaOrder(String faq_type) {
        List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();

        Map<String, String> data = new HashMap<>();
        List<FaqBoard> qnaList = faqBoardRepository.findAllByFaqType(faq_type);

        for(FaqBoard q:qnaList) {
            data.put("faq_no", String.valueOf(q.getFaqNo()));
            data.put("faq_title", q.getFaqTitle());
            data.put("faq_content", q.getFaqContent());
            data.put("faq_date", q.getFaqDate());
            data.put("faq_type", q.getFaqType());
            dataList.add(data);
        }

        return dataList;
    }
}
