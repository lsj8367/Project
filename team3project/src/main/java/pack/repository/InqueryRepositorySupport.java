package pack.repository;

import java.util.List;
import pack.domain.entity.Inquery;

public interface InqueryRepositorySupport {
    List<Inquery> findAllOrderByInqOnumASC();
    Long getMaxNum();
    Long updateOnum(int inqOnum, int inqGnum);
}
