package pack.user.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.domain.entity.OldBook;
import pack.repository.OldBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RentBookService {
    private final OldBookRepository oldBookRepository;

    public OldBook rentalInfo(String rent_no) {
        return oldBookRepository.oldBookInfoRentalState(Long.valueOf(rent_no));
    }

}
