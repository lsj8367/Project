package pack.repository;

import com.querydsl.core.Tuple;
import pack.domain.entity.Inquery;

import java.util.List;

public interface InqueryRepositorySupport {
    List<Inquery> findAllOrderByInqOnumASC();
    List<Tuple> selectInqList(final String inqId);
    Long getMaxNum();
    Long updateOnum(int inqOnum, int inqGnum);
}
