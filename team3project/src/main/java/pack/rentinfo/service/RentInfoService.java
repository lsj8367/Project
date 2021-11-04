package pack.rentinfo.service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.rentinfo.domain.Rentinfo;
import pack.rentinfo.repository.RentinfoRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RentInfoService {

    private final RentinfoRepository rentinfoRepository;

    public List<Month> rentMonth() {
        final List<Rentinfo> rentinfos = rentinfoRepository.rentMonth();

        return rentinfos.stream().map(
            rentinfo -> rentinfo.getRentSdate().getMonth()
        ).collect(Collectors.toList());
    }

}
