package pack.inquery.repository;

import java.util.List;
import pack.inquery.domain.Inquery;

public interface InqueryRepositorySupport {
    List<Inquery> findAllOrderByInqOnumASC();
    Long getMaxNum();
    Long updateOnum(int inqOnum, int inqGnum);
}
