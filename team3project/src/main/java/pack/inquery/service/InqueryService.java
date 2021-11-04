package pack.inquery.service;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.inquery.domain.Inquery;
import pack.inquery.model.InqueryDto;
import pack.inquery.repository.InqueryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class InqueryService {

    private final InqueryRepository inqueryRepository;

    public List<Inquery> findAllOrderByInqOnumASC() {
        return inqueryRepository.findAllOrderByInqOnumASC();
    }

    public boolean insertInquery(InqueryDto inqueryDto) {
        try {
            inqueryRepository.save(Inquery.toEntity(inqueryDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Inquery> selectInqList(String inqId) {
        return inqueryRepository.findByInqIdOrderByInqNoAsc(inqId);
    }

}
