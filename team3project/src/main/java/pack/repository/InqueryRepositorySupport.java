package pack.repository;

import pack.domain.entity.Inquery;

import java.util.List;

public interface InqueryRepositorySupport {
    List<Inquery> findAllOrderByInqOnumASC();
    List<Inquery> selectInqList(final String inqId);
}
