package pack.rentinfo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void insertRentInfo(String rentId) {
        rentinfoRepository.save(Rentinfo.builder()
            .rentId(rentId)
            .build());
    }

    public void delete(long id) {
        rentinfoRepository.deleteById(id);
    }

    public void updateState(int rentNo) {
        final Rentinfo rentinfo = rentinfoRepository.findById((long) rentNo)
            .orElseThrow(() -> new RuntimeException("해당하는 대여 정보가 없음"));
        updateEdate(rentinfo);
        updateEcount(rentinfo);
    }

    private void updateEdate(Rentinfo rentinfo) {
        LocalDateTime localDateTime = LocalDateTime.parse(rentinfo.getRentEdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        localDateTime = localDateTime.plusDays(6);
        rentinfo.setRentEdate(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private void updateEcount(Rentinfo rentinfo) {
        rentinfo.setRentEcount(1);
    }

}
