package pack.user.service;

import static pack.model.Grade.FIRST_GRADE;
import static pack.model.Grade.SECOND_GRADE;
import static pack.model.Grade.THIRD_GRADE;
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

    public List<OldBook> genreForFirstGrade(String obGenre) {
        return oldBookRepository.findAllByObStateAndObDonorNotAndObGenreContains(FIRST_GRADE.getGrade(), "10", obGenre);
    }

    public List<OldBook> genreForAnotherGrade(String ob_genre) {
        return oldBookRepository.findAllByObStateInAndObGenreContains(List.of(SECOND_GRADE.getGrade(), THIRD_GRADE.getGrade()), ob_genre);
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
        return oldBookRepository.findAllByObStateAndObDonorNotOrderByObNoDesc(FIRST_GRADE.getGrade(), "10");
    }

    public List<OldBook> oldLow() {
        return oldBookRepository.findAllByObStateOrObStateOrderByObNoDesc(SECOND_GRADE.getGrade(), THIRD_GRADE.getGrade());
    }

}
