package pack.newbook.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import pack.newbook.domain.NewBook;
import pack.newbook.repository.NewBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class NewBookService {

    private static final int LIMIT_NUMBER = 10;

    private final NewBookRepository newBookRepository;

    public NewBook bestSeller() {
        return newBookRepository.selectBest();
    }

    public List<NewBook> readTop3() {
        return newBookRepository.selectReadTop3();
    }

    public List<NewBook> random10() {
        return newBookRepository.selectRandom(LIMIT_NUMBER);
    }

    public List<NewBook> selectGenre(String nbGenre) {
        return newBookRepository.selectGenre(nbGenre);
    }

    public List<NewBook> selectBest30() {
        return newBookRepository.selectBest30();
    }

    public List<NewBook> selectNew() {
        return newBookRepository.selectNew();
    }

    public List<NewBook> selectBookAll() {
        return newBookRepository.findAll(Sort.by(Direction.DESC, "id"));
    }

    @Transactional
    public void plusReadCnt(int nbNo) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nbNo);
        optionalNewBook.ifPresent(newBook -> {
            newBook.setNbReadcnt(newBook.getNbReadcnt() + 1);
        });
    }

    public NewBook selectNewBook(Long nbNo) {
        return newBookRepository.findById(nbNo).orElseThrow(NullPointerException::new);
    }

    public List<NewBook> selectAuthorList(String nbAuthor) {
        return newBookRepository.selectAuthorList(nbAuthor);
    }

    public List<NewBook> getDataNewAllExist(String nbName) {
        return newBookRepository.getDataNewAllExist(nbName);
    }

    public void updateScount(int nb_no, int order_scount) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nb_no);

        optionalNewBook.ifPresent(newBook -> {
            newBook.setNbScount(newBook.getNbScount() - order_scount);
            newBook.setNbStock(newBook.getNbStock() + order_scount);
        });
    }

    public NewBook recommandNewBook() {
        return newBookRepository.recommandNewBook();
    }

}
