package pack.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.domain.entity.FaqBoard;
import pack.repository.FaqBoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaPartService {
    private final FaqBoardRepository faqBoardRepository;

    public List<Map<String, String>> qnaPart(String qna_class) {
        List<Map<String, String>> dataList = new ArrayList<>();

        Map<String, String> data = new HashMap<>();
        List<FaqBoard> qnaList = faqBoardRepository.findAllByFaqType(qna_class);

        qnaList.forEach(faqBoard -> {
            data.put("faq_no", String.valueOf(faqBoard.getFaqNo()));
            data.put("faq_title", faqBoard.getFaqTitle());
            data.put("faq_content", faqBoard.getFaqContent());
            data.put("faq_date", faqBoard.getFaqDate());
            data.put("faq_type", faqBoard.getFaqType());
            dataList.add(data);
        });

        return dataList;
    }


}
