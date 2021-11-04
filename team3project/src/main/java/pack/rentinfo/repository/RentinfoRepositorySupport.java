package pack.rentinfo.repository;

import java.util.List;
import pack.rentinfo.domain.Rentinfo;

public interface RentinfoRepositorySupport {
    List<Rentinfo> findAllByRentBook();
    List<Rentinfo> rentMonth();
}
