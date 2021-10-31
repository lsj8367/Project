package pack.orderinfo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.orderinfo.domain.Orderinfo;

public interface OrderinfoRepository extends JpaRepository<Orderinfo, Long>, OrderRepositorySupport {
    Orderinfo findByOrderNoAndOrderPasswd(Long orderNo, String orderPasswd);
    List<Orderinfo> findAllByOrderlistNo(String orderlistNo);
    Optional<Orderinfo> findByOrderlistNo(String orderlistNo);
    Optional<Orderinfo> findByOrderlistNoAndOrderPasswd(String orderlistNo, String orderPasswd);
    List<Orderinfo> findTop3ByOrderIdOrderByOrderDateDesc(String orderId);
    List<Orderinfo> findAllByOrderIdOrderByOrderDateDesc(String orderId);
    List<Orderinfo> findByOrderPerson(String orderPerson, Sort sort);
    Long deleteByOrderlistNo(String orderlistNo);
}