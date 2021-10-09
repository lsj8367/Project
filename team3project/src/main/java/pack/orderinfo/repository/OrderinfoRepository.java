package pack.orderinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.orderinfo.domain.Orderinfo;

public interface OrderinfoRepository extends JpaRepository<Orderinfo, Long>, OrderRepositorySupport {

}