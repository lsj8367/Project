package pack.user.service;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.domain.entity.OldBook;
import pack.model.Grade;
import pack.repository.OldBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RentBookService {
    private final OldBookRepository oldBookRepository;

    public OldBook rentalInfo(String rent_no) {
        return oldBookRepository.findByObStateInAndObNo(List.of(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade()), Long.valueOf(rent_no));
    }

}
