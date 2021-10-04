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

    public boolean insertingReply(InqueryDto inqueryDto) {
        try {
            inqueryRepository.save(Inquery.builder()
                .inqTitle(inqueryDto.getInq_title())
                .inqContext(inqueryDto.getInq_context())
                .inqId(inqueryDto.getInq_id())
                .inqGnum(inqueryDto.getInq_gnum())
                .inqOnum(inqueryDto.getInq_onum())
                .inqNested(inqueryDto.getInq_nested())
                .build());

            return true;
        } catch (Exception e) {
            return false;
        }
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

    public Long getMaxNum() {
        return inqueryRepository.getMaxNum();
    }

    public List<Inquery> selectInqPart(Long inqNo) {
        return inqueryRepository.findAllByInqNo(inqNo);
    }

    public boolean updateOnum(int inqOnum, int inqGnum) {
        try {
            inqueryRepository.updateOnum(inqOnum, inqGnum);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
