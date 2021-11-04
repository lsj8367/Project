package pack.rentinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.rentinfo.domain.Rentinfo;

public interface RentinfoRepository extends JpaRepository<Rentinfo, Long>, RentinfoRepositorySupport{
}