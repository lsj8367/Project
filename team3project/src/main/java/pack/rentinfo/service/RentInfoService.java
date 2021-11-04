package pack.rentinfo.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.rentinfo.repository.RentinfoRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RentInfoService {

    private final RentinfoRepository rentinfoRepository;

}
