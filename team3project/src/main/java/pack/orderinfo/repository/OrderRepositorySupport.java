package pack.orderinfo.repository;

import java.util.List;
import pack.orderinfo.domain.Orderinfo;

public interface OrderRepositorySupport {
    List<Orderinfo> selectNewBookOrderAll();
    List<Orderinfo> selectOldBookOrderAll();
    List<Orderinfo> findAllByOrderStateGroupByOrderListNo();
    List<Orderinfo> notEqualOrderStateGroupByOrderListNo();
    List<Orderinfo> findOldBookOrderListAll(String orderId);
    List<Orderinfo> findNewBookOrderListAll(String orderId);
}
