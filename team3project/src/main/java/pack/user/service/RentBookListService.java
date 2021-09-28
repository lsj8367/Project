package pack.user.service;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.domain.entity.OldBook;
import pack.repository.OldBookRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RentBookListService {
    private final OldBookRepository oldBookRepository;

    public void flush() {
        oldBookRepository.flush();
    }

    public List<OldBook> genreForFirstGrade(String ob_genre) {
        return oldBookRepository.genreForFirstGrade(ob_genre);
    }

    public List<OldBook> genreForAnotherGrade(String ob_genre) {
        return oldBookRepository.genreForAnotherGrade(ob_genre);
    }

    public List<OldBook> randomFirstGrade() {
        return oldBookRepository.oldRandom();
    }

    public List<OldBook> oldLowLimit2() {
        return oldBookRepository.oldLowLimit2();
    }

    public OldBook selectBestRentBook() {
        return oldBookRepository.selectBestRentBook();
    }

    public List<OldBook> oldHigh() {
        return oldBookRepository.oldHigh();
    }

    public List<OldBook> oldLow() {
        return oldBookRepository.oldLow();
    }

}
