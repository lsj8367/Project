package pack.orderinfo.repository;

import java.util.List;
import pack.orderinfo.domain.Orderinfo;

public interface OrderRepositorySupport {
    List<Orderinfo> selectNewBookOrderAll();
    List<Orderinfo> selectOldBookOrderAll();
}
