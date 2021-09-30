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
public class OldBookService {
    private final OldBookRepository oldBookRepository;


    public OldBook bookInfo(String book_no) {
        return oldBookRepository.findByObStateAndObNo("1", Long.valueOf(book_no));
    }

    public OldBook rentalInfo(String book_no) {
        return oldBookRepository.findByObStateInAndObNo(List.of(Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade()), Long.valueOf(book_no));
    }

}
